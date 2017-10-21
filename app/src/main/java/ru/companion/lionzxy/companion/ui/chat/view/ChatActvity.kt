package ru.companion.lionzxy.companion.ui.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.data.models.MessageModel
import ru.companion.lionzxy.companion.ui.chat.presenter.ChatPresenter

class ChatActvity : MvpAppCompatActivity(), ChatView {

    companion object {
        const val EXTRA_DIALOG = "dialog"

        fun getIntent(context: Context, model: DialogModel): Intent {
            val intent = Intent(context, ChatActvity::class.java)
            intent.putExtra(EXTRA_DIALOG, model)
            return intent
        }
    }

    @InjectPresenter
    lateinit var presenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        progressBarGlobal.visibility = View.VISIBLE
        parent_ll.visibility = View.GONE
        presenter.loadMessage(getDialog().id)
        toolbar.title = "${getDialog().user.firstName} ${getDialog().user.lastName}"
        sendButton.setOnClickListener({
            progressBar.visibility = View.VISIBLE
            sendButton.visibility = View.GONE
            presenter.send(getDialog().id, inputEdit.text.toString())
        })
    }

    override fun onChatLoad(messages: List<MessageModel>) {
        progressBar.visibility = View.GONE
        sendButton.visibility = View.VISIBLE
        progressBarGlobal.visibility = View.GONE
        parent_ll.visibility = View.VISIBLE
        recyclerView.adapter = ChatAdapter(messages.reversed())
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager
    }

    fun getDialog(): DialogModel = intent.getParcelableExtra(EXTRA_DIALOG)

    override fun clearInput() {
        inputEdit.text.clear()
    }
}