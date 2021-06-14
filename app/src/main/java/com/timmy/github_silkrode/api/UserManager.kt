package com.timmy.github_silkrode.api

object UserManager {
    lateinit var INSTANCE: UserInfo

    val isThingInitialized get() = this::INSTANCE.isInitialized
}