package com.example.cometchatandroidsample

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chat.constants.CometChatConstants
import com.cometchat.chat.core.CometChat
import com.cometchat.chat.models.TextMessage
import com.cometchat.chat.models.Group
import com.cometchat.chat.models.User
import com.cometchat.chat.exceptions.CometChatException
import com.example.cometchatandroidsample.adapters.MentorsAdapter
import com.example.cometchatandroidsample.adapters.GroupsAdapter

class EducationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        // Join Group Button
        val joinGroupButton = findViewById<Button>(R.id.joinGroupButton)
        joinGroupButton.setOnClickListener { joinLearningGroup() }

        // Send Learning Tip Button
        val sendTipButton = findViewById<Button>(R.id.sendTipButton)
        sendTipButton.setOnClickListener { sendLearningTip() }

        // Mentors List
        val mentorsList = findViewById<ListView>(R.id.mentorsList)
        val mentors = listOf(
            "Dr. Priya Sharma (Computer Science)",
            "Ms. Kavita Verma (Digital Marketing)",
            "Dr. Meera Kapoor (Psychology)"
        )
        val mentorsAdapter = MentorsAdapter(this, mentors)
        mentorsList.adapter = mentorsAdapter

        // Learning Groups List
        val groupsList = findViewById<ListView>(R.id.groupsList)
        val groups = listOf(
            "Women in Tech",
            "Digital Marketing 101",
            "Mental Health Awareness"
        )
        val groupsAdapter = GroupsAdapter(this, groups)
        groupsList.adapter = groupsAdapter
    }

    // Join Learning Group
    private fun joinLearningGroup() {
        val groupId = "wit" // Replace with your actual group ID
        val groupName = "Women in Tech"
        val groupType = CometChatConstants.GROUP_TYPE_PUBLIC

        // Create a new group or join an existing group
        CometChat.joinGroup(groupId, groupType, null, object : CometChat.CallbackListener<Group>() {
            override fun onSuccess(group: Group) {
                Toast.makeText(
                    this@EducationActivity,
                    "Successfully joined the group: $groupName",
                    Toast.LENGTH_SHORT
                ).show()
            }


            override fun onError(e: CometChatException) {
                Toast.makeText(
                    this@EducationActivity,
                    "Failed to join the group: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    // Send Daily Learning Tip via Message
    private fun sendLearningTip() {
        val messageText = "Did you know? Digital marketing is a growing field with great opportunities for women. Start learning today!"

        // Send a Text Message
        val chatGroupId = "wit" // Replace with your group ID
        val textMessage = TextMessage(chatGroupId, messageText, CometChatConstants.RECEIVER_TYPE_GROUP)

        CometChat.sendMessage(textMessage, object : CometChat.CallbackListener<TextMessage>() {
            override fun onSuccess(message: TextMessage) {
                Toast.makeText(
                    this@EducationActivity,
                    "Learning tip sent successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(e: CometChatException) {
                Toast.makeText(
                    this@EducationActivity,
                    "Failed to send learning tip: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    // Send Private Message to Mentor
    private fun sendPrivateMessageToMentor(mentorId: String) {
        val messageText = "Hello, I am looking for some guidance on digital marketing. Can you help?"

        // Send a Private Text Message
        val textMessage = TextMessage(mentorId, messageText, CometChatConstants.RECEIVER_TYPE_USER)

        CometChat.sendMessage(textMessage, object : CometChat.CallbackListener<TextMessage>() {
            override fun onSuccess(message: TextMessage) {
                Toast.makeText(
                    this@EducationActivity,
                    "Message sent to mentor successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(e: CometChatException) {
                Toast.makeText(
                    this@EducationActivity,
                    "Failed to send message to mentor: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
