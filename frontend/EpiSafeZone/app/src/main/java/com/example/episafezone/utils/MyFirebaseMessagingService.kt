package com.example.episafezone.utils

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.episafezone.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    @SuppressLint("MissingPermission")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Obtén los datos del mensaje
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        // Crea un constructor de notificaciones
        val builder = NotificationCompat.Builder(this, "your_channel_id")
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)

        // Crea un administrador de notificaciones
        val notificationManager = NotificationManagerCompat.from(this)

        // Emite la notificación
        notificationManager.notify(1, builder.build())
    }
}