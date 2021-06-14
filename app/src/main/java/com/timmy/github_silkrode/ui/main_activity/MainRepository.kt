package com.timmy.github_silkrode.ui.main_activity

import android.annotation.SuppressLint
import com.timmy.github_silkrode.api.UserManager
import com.timmy.github_silkrode.ui.mvvm_util.BaseRepositoryBoth
import com.timmy.github_silkrode.ui.mvvm_util.ILocalDataSource
import com.timmy.github_silkrode.ui.mvvm_util.IRemoteDataSource
import javax.inject.Inject
import com.timmy.github_silkrode.api.ServiceManager
import com.timmy.github_silkrode.db.UserDatabase
import com.timmy.github_silkrode.BuildConfig
import com.timmy.github_silkrode.api.UserInfo
import com.timmy.github_silkrode.base.Results
import com.timmy.github_silkrode.ext.processApiResponse
import util.loge
import util.logi

class MainRepository @Inject constructor(
    remoteDataSource: UserRemoteDataSource,
    localDataSource: UserLocalDataSource
) : BaseRepositoryBoth<UserRemoteDataSource, UserLocalDataSource>(remoteDataSource, localDataSource) {

    suspend fun login(): Results<UserInfo> {
        val userInfo = remoteDataSource.login()
        when (userInfo) {
            is Results.Failure -> {
                loge("login", "fail!")
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

}

data class AutoLoginEvent(
    val autoLogin: Boolean,
    val username: String,
    val password: String
)