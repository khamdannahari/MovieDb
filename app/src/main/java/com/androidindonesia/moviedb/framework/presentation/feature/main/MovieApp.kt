package com.androidindonesia.moviedb.framework.presentation.feature.main

import android.app.Application
import com.blankj.utilcode.util.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}
