package com.example.cometchatandroidsample

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chat.constants.CometChatConstants
import com.cometchat.chat.exceptions.CometChatException
import com.cometchat.chat.models.TextMessage
import com.cometchat.chat.core.CometChat
import com.example.cometchatandroidsample.adapters.SafetyTipsAdapter

class SecurityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        // SOS Button
        val sosButton = findViewById<Button>(R.id.sosButton)
        sosButton.setOnClickListener { sendSOSMessage() }

        // Safety Tips List
        val safetyTipsList = findViewById<ListView>(R.id.safetyTipsList)
        val tips = listOf(
            "Avoid walking alone at night.",
            "Share your live location with friends.",
            "Stay alert and avoid distractions like headphones.",
            "Know your emergency contacts.",
            "Trust your instincts in unfamiliar situations."
        )
        val adapter = SafetyTipsAdapter(this, tips)
        safetyTipsList.adapter = adapter
    }

    // Send SOS Message via CometChat
    private fun sendSOSMessage() {
        val chatGroupId = "adi0038" // Replace with your group ID
        val messageText = "This is an emergency! I need help."

        // Create a TextMessage
        val textMessage = TextMessage(chatGroupId, messageText, CometChatConstants.RECEIVER_TYPE_GROUP)

        // Send the message using CometChat
        CometChat.sendMessage(textMessage, object : CometChat.CallbackListener<TextMessage>() {
            override fun onSuccess(message: TextMessage) {
                Toast.makeText(
                    this@SecurityActivity,
                    "SOS message sent successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(e: CometChatException) {
                Toast.makeText(
                    this@SecurityActivity,
                    "Failed to send SOS message: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
