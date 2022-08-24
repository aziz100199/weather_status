package com.example.weatherupdates

import android.app.Application
import com.example.weatherupdates.weather_data_base.WeatherDatabase
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        hInitTimber()
    }

    private fun hInitTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    message: String,
                    t: Throwable?,
                ) {
                    super.log(priority, String.format(hTag, tag), message, t)
                }
            })
        }
    }

    companion object {
        const val hTag = "timberTag %s"
    }
}