package com.timmy.github_silkrode.ui.main_activity

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
import com.timmy.github_silkrode.BuildConfig
import com.timmy.github_silkrode.api.UserInfo
import com.timmy.github_silkrode.base.Results
import com.timmy.github_silkrode.ext.globalPagingConfig
import com.timmy.github_silkrode.ext.processApiResponse
import kotlinx.coroutines.flow.Flow
import util.logi

class MainRepository @Inject constructor(
    remoteDataSource: UserRemoteDataSource,
    localDataSource: UserLocalDataSource
) : BaseRepositoryBoth<UserRemoteDataSource, UserLocalDataSource>(remoteDataSource, localDataSource) {

    suspend fun login(): Results<UserInfo> {
        val userInfo = remoteDataSource.login()
        when (userInfo) {
            is Results.Failure -> {
                logi("login", "fail!")
            }
            is Results.Success -> UserManager.INSTANCE = requireNotNull(userInfo.data)
        }
        return userInfo
    }
}

class UserRemoteDataSource @Inject constructor(
    private val serviceManager: ServiceManager
) : IRemoteDataSource {

    suspend fun login(): Results<UserInfo> {
        val auth = "token ${BuildConfig.USER_ACCESS_TOKEN}"
        logi("login", "auth===>$auth")
        return processApiResponse { serviceManager.userService.fetchUserOwner(auth) }
    }
}

class UserLocalDataSource @Inject constructor(
    private val db: UserDatabase
) : ILocalDataSource {

//    suspend fun savePrefUser(username: String, password: String) {
//        userRepository.saveUserInfo(username, password)
//    }
//
//    suspend fun clearPrefsUser() {
//        userRepository.saveUserInfo("", "")
//    }
//
//    fun fetchAutoLogin(): Flow<AutoLoginEvent> {
//        return userRepository.fetchUserInfoFlow()
//            .map { user ->
//                val username = user.username
//                val password = user.password
//                val isAutoLogin = user.autoLogin
//                when (username.isNotEmpty() && password.isNotEmpty() && isAutoLogin) {
//                    true -> AutoLoginEvent(true, username, password)
//                    false -> AutoLoginEvent(false, "", "")
//                }
//            }
//    }
}

data class AutoLoginEvent(
    val autoLogin: Boolean,
    val username: String,
    val password: String
)