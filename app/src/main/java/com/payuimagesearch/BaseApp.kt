package com.payuimagesearch

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : MultiDexApplication() {

    private val CHANNEL_ID: String ="payu_notification_channel"

    init {
        instance = this
    }

    companion object {
        var context: Context? = null
        private var instance: BaseApp? = null

        @JvmStatic
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Payu Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            serviceChannel.setShowBadge(true)
            val manager =
                getSystemService(
                    NotificationManager::class.java
                )
            manager.createNotificationChannel(serviceChannel)
        }
    }
}