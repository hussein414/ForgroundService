package com.example.forgroundservice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.forgroundservice.R
import com.example.forgroundservice.utils.Constance


class RunningService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> startAction()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startAction() {
        val notification = NotificationCompat.Builder(this, Constance.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is Active")
            .setContentText("Elapses Time :4:45")
            .build()
        startForeground(Constance.FOREGROUND_SERVICE_ID, notification)
    }
}