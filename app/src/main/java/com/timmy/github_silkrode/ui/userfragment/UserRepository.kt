package com.timmy.github_silkrode.ui.userfragment

import android.annotation.SuppressLint
import com.timmy.github_silkrode.api.UserManager
import com.timmy.github_silkrode.ui.mvvm_util.BaseRepositoryBoth
import com.timmy.github_silkrode.ui.mvvm_util.ILocalDataSource
import com.timmy.github_silkrode.ui.mvvm_util.IRemoteDataSource
import javax.inject.Inject
import androidx.paging.*
import com.qingmei2.sample.PAGING_REMOTE_PAGE_SIZE
import com.timmy.github_silkrode.api.ServiceManager
import com.timmy.github_silkrode.db.ReceivedEvent
import com.timmy.github_silkrode.db.UserDatabase
import androidx.room.withTransaction
import util.globalPagingConfig

class UserRepository @Inject constructor(
    remoteDataSource: UserRemoteDataSource,
    localDataSource: UserLocalDataSource
) : BaseRepositoryBoth<UserRemoteDataSource, UserLocalDataSource>(remoteDataSource,localDataSource) {

    @ExperimentalPagingApi
    fun fetchPager(): Pager<Int, ReceivedEvent> {
        val username: String = UserManager.INSTANCE.login
        val remoteMediator = UserRemoteMediator(username, remoteDataSource, localDataSource)
        return Pager(
                config = globalPagingConfig,
                remoteMediator = remoteMediator,
                pagingSourceFactory = { localDataSource.fetchPagedListFromLocal() }
        )
    }
}

class UserRemoteDataSource @Inject constructor(private val serviceManager: ServiceManager) : IRemoteDataSource {

    suspend fun queryReceivedEvents(username: String,
                                    pageIndex: Int,
                                    perPage: Int): List<ReceivedEvent> {
        return serviceManager.userService.queryReceivedEvents(username, pageIndex, perPage)
    }
}

@SuppressLint("CheckResult")
class UserLocalDataSource @Inject constructor(private val db: UserDatabase) : ILocalDataSource {
//
    fun fetchPagedListFromLocal(): PagingSource<Int, ReceivedEvent> {
        return db.userReceivedEventDao().queryEvents()
    }

    suspend fun clearAndInsertNewData(data: List<ReceivedEvent>) {
        db.withTransaction {
            db.userReceivedEventDao().clearReceivedEvents()
            insertDataInternal(data)
        }
    }

    suspend fun insertNewPagedEventData(newPage: List<ReceivedEvent>) {
        db.withTransaction { insertDataInternal(newPage) }
    }

    suspend fun fetchNextIndex(): Int {
        return db.withTransaction {
            db.userReceivedEventDao().getNextIndexInReceivedEvents() ?: 0
        }
    }

    private suspend fun insertDataInternal(newPage: List<ReceivedEvent>) {
        val start = db.userReceivedEventDao().getNextIndexInReceivedEvents() ?: 0
        val items = newPage.mapIndexed { index, child ->
            child.indexInResponse = start + index
            child
        }
        db.userReceivedEventDao().insert(items)
    }
}

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val username: String,
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : RemoteMediator<Int, ReceivedEvent>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ReceivedEvent>): MediatorResult {
        return try {
            val pageIndex = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val nextIndex = localDataSource.fetchNextIndex()
                    if (nextIndex % PAGING_REMOTE_PAGE_SIZE != 0) {
                        return MediatorResult.Success(true)
                    }
                    nextIndex / PAGING_REMOTE_PAGE_SIZE + 1
                }
            }
            val data = remoteDataSource.queryReceivedEvents(username, pageIndex, PAGING_REMOTE_PAGE_SIZE)
            if (loadType == LoadType.REFRESH) {
                localDataSource.clearAndInsertNewData(data)
            } else {
                localDataSource.insertNewPagedEventData(data)
            }
            MediatorResult.Success(data.isEmpty())
        } catch (exception: Exception) {
//            toast (exception.toString())
            MediatorResult.Error(exception)
        }
    }

}
