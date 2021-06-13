package com.timmy.github_silkrode.ui.userfragment.minefragment

import com.timmy.github_silkrode.api.ServiceManager
import com.timmy.github_silkrode.ui.mvvm_util.BaseRepositoryRemote
import com.timmy.github_silkrode.ui.mvvm_util.IRemoteDataSource
import javax.inject.Inject

interface IRemoteProfileDataSource : IRemoteDataSource

class MineRepository @Inject constructor(
        remoteDataSource: MineRemoteDataSource
) : BaseRepositoryRemote<IRemoteProfileDataSource>(remoteDataSource)

class MineRemoteDataSource @Inject constructor(
        val serviceManager: ServiceManager
) : IRemoteProfileDataSource
