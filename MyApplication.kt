package com.example.cometchatandroidsample

import android.app.Application
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.AppSettings

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Configure App Settings
        val appSettings = AppSettings.AppSettingsBuilder()
            .subscribePresenceForAllUsers() // Optional: Enable presence
            .setRegion("us")               // Set the region (check your CometChat dashboard for your region)
            .build()

        // Initialize CometChat
        CometChat.init(this, "2695836e830bc0a2", object : CometChat.CallbackListener<String>() {
            override fun onSuccess(success: String) {
              Log.d("CometChatInit", "Initialization completed successfully")
            }

            override fun onError(e: CometChatException?) {
               Log.e("CometChatInit", "Initialization failed with exception: ${e.message}")
                e?.message?.let { errorMessage ->
                    // Log or handle the error message
                }
            }
        })
    }
}
