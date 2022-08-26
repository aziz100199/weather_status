package com.example.weatherupdates

import android.app.Application
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    message: String,
                    t: Throwable?,
                ) {
                    super.log(priority, String.format(timberTag, tag), message, t)
                }
            })
        }
    }

    companion object {
        const val timberTag = "timberTag %s"
    }
}