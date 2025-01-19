package com.example.cometchatandroidsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.cometchat.chat.core.CometChat
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chat.models.User
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initEventHandlers()
    }

    private fun initEventHandlers() {
        val loginButton = this.findViewById<AppCompatButton>(R.id.login_button1)
        loginButton.setOnClickListener {
            val username = this.findViewById<TextInputEditText>(R.id.userId1)
            // use password while logging in to your backend system
            val password = this.findViewById<TextInputEditText>(R.id.passwordInput1)
            login(uid = username.text.toString())
        }
    }

    private fun login(uid: String) {
        if (CometChat.getLoggedInUser() == null) {
            CometChat.login(uid, Constants.AUTH_KEY, object : CometChat.CallbackListener<User>() {
                override fun onSuccess(p0: User?) {
                    Log.d(TAG, "Login Successful : " + p0?.toString())
                    registerPushToken()
                    startActivity(Intent(this@LoginActivity, HomePageActivity::class.java))
                }

                override fun onError(p0: CometChatException?) {
                    Log.d(TAG, "Login failed with exception: " + p0?.message)
                }

            })
        } else {
            // User already logged in
            Log.d(TAG, "User is already logged in: " + CometChat.getLoggedInUser())
            startActivity(Intent(this@LoginActivity, HomePageActivity::class.java))
            finish()
        }
    }

    private fun registerPushToken() {
        MyFirebaseMessagingService.token?.let {
            CometChat
                .registerTokenForPushNotification(
                    it,
                    object : CometChat.CallbackListener<String?>() {
                        override fun onSuccess(s: String?) {

                        }

                        override fun onError(e: CometChatException) {
                            Log.e(TAG, e.message ?: "Error occurred")
                        }
                    })
        }
    }
}