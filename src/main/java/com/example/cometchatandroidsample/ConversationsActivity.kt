package com.example.cometchatandroidsample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chat.models.Group
import com.cometchat.chat.models.User
import com.cometchat.chatuikit.conversations.ConversationsConfiguration
import com.cometchat.chatuikit.conversationswithmessages.CometChatConversationsWithMessages
import com.cometchat.chatuikit.messagecomposer.MessageComposerConfiguration
import com.cometchat.chatuikit.messages.MessagesConfiguration
import com.cometchat.chatuikit.shared.framework.ChatConfigurator
import com.cometchat.chatuikit.shared.models.CometChatMessageComposerAction


class ConversationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setConversationsConfig()
    }
    //This is function
    private fun setConversationsConfig() {
        val conversationsConfiguration: ConversationsConfiguration? =
            ConversationsConfiguration()
                .disableTyping(false)
                .disableUsersPresence(false)
                .disableReceipt(false)

        val conversations = CometChatConversationsWithMessages(this)

        val messagesConfig = MessagesConfiguration()
            .setDisableTyping(true)

        val messageComposerConfig = MessageComposerConfiguration()
        messageComposerConfig.attachmentOption = getAttachmentOptions()

        messagesConfig.messageComposerConfiguration = messageComposerConfig
        conversations.messagesConfiguration = messagesConfig

        conversations.setConversationsConfiguration(conversationsConfiguration)
        setContentView(conversations)
    }

    private fun getAttachmentOptions() = fun(
        context: Context?,
        user: User?,
        group: Group?,
        stringStringHashMap: HashMap<String?, String?>?
    ): MutableList<CometChatMessageComposerAction> {
        val actionList: MutableList<CometChatMessageComposerAction> =
            ChatConfigurator.getDataSource()
                .getAttachmentOptions(context, user, group, stringStringHashMap)
        actionList.add(
            CometChatMessageComposerAction()
                .setTitle("Custom attachment")
                .setId("custom_attachment")
                .setOnClick {
                    Toast.makeText(context, "Custom attachment clicked", Toast.LENGTH_SHORT).show()
                }
        )
        return actionList
    }
}