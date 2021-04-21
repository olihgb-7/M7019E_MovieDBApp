package com.ltu.m7019e.m7019e_moviedbapp

import android.app.Application
import timber.log.Timber

class MovieDBApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}