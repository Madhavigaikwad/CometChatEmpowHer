package com.example.cometchatandroidsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.cometchat.chat.core.CometChat
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chatuikit.shared.cometchatuikit.CometChatUIKit
import com.cometchat.chatuikit.shared.cometchatuikit.UIKitSettings


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCometChatUiKit()
        initEventHandlers()
        checkLoginStatus()
    }

    private fun initEventHandlers() {
        val goToLoginBtn = this.findViewById<AppCompatButton>(R.id.go_to_login1)
        goToLoginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val goToSignupBtn = this.findViewById<AppCompatButton>(R.id.go_to_signup1)
        goToSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun checkLoginStatus() {
        if (CometChat.getLoggedInUser() != null) {
            // User already logged in
            Log.d(TAG, "User is already logged in: " + CometChat.getLoggedInUser())
            startActivity(Intent(this@MainActivity, HomePageActivity::class.java))
            finish()
        }
    }

    private fun initCometChatUiKit() {
        val uiKitSettings = UIKitSettings.UIKitSettingsBuilder()
            .setRegion(Constants.REGION)
            .setAppId(Constants.APP_ID)
            .setAuthKey(Constants.AUTH_KEY)
            .subscribePresenceForAllUsers().build()

        CometChatUIKit.init(this, uiKitSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(successString: String?) {}
            override fun onError(e: CometChatException?) {}
        })
    }
}