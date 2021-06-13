package com.timmy.github_silkrode.ui.mvvm_util

open class BaseRepositoryBoth<T : IRemoteDataSource, R : ILocalDataSource>(
        val remoteDataSource: T,
        val localDataSource: R
) : IRepository

open class BaseRepositoryLocal<T : ILocalDataSource>(
        val remoteDataSource: T
) : IRepository

open class BaseRepositoryRemote<T : IRemoteDataSource>(
        val remoteDataSource: T
) : IRepository

open class BaseRepositoryNothing() : IRepository