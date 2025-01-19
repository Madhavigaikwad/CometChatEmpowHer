package com.example.cometchatandroidsample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.cometchat.chat.helpers.CometChatHelper
import com.cometchat.chat.models.BaseMessage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.Date

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private var json: JSONObject? = null
    private val intent: Intent? = null
    private var count = 0
    override fun onNewToken(s: String) {
        token = s
        Log.d(TAG, "onNewToken: $s")
    }
//This is function
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        try {
            count++
            json = JSONObject(remoteMessage.data as Map<*, *>?)
            Log.d(TAG, "JSONObject: " + json.toString())
            val messageData = JSONObject(json!!.getString("message"))
            val baseMessage = CometChatHelper
                .processMessage(JSONObject(remoteMessage.data["message"]))
            showNotifcation(baseMessage)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun getBitmapFromURL(strURL: String?): Bitmap? {
        return if (strURL != null) {
            try {
                val url = URL(strURL)
                val connection =
                    url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    private fun showNotifcation(baseMessage: BaseMessage) {
        try {
            val m = Date().time.toInt()
            val GROUP_ID = "group_id"
            val builder = NotificationCompat.Builder(this, "2")
                .setContentTitle(json!!.getString("title"))
                .setContentText(json!!.getString("alert"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setColor(resources.getColor(R.color.white))
                .setLargeIcon(getBitmapFromURL(baseMessage.sender.avatar))
                .setGroup(GROUP_ID)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            val summaryBuilder = NotificationCompat.Builder(this, "2")
                .setContentTitle("CometChat")
                .setContentText("$count messages")
                .setGroup(GROUP_ID)
                .setGroupSummary(true)
            val notificationManager = NotificationManagerCompat.from(this)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                return
            }
            notificationManager.notify(baseMessage.id, builder.build())
            notificationManager.notify(0, summaryBuilder.build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val TAG = "MyFirebaseService"
        var token: String? = null
    }
}