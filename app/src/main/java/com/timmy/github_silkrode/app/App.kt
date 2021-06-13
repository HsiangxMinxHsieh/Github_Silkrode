package com.timmy.github_silkrode.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    val TAG = javaClass.simpleName

    override fun onCreate() {
        super.onCreate()
    }
}