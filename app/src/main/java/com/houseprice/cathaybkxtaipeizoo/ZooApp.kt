package com.houseprice.cathaybkxtaipeizoo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class ZooApp : MultiDexApplication() {
    companion object {
        var application: Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        application = this
    }

}