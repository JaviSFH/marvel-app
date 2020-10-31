package com.tuppersoft.marvel

import android.app.Application
import com.facebook.stetho.Stetho
import com.tuppersoft.marvel.core.extension.logd
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        "Init app".logd()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
