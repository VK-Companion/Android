package ru.companion.lionzxy.companion.ui.chat.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.element_chat_message.view.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.MessageModel
import ru.companion.lionzxy.companion.utils.inflate

class ChatAdapter(private val messages: List<MessageModel>) : RecyclerView.Adapter<ChatAdapter.MessageHolder>() {
    override fun getItemCount() = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        return MessageHolder(parent.inflate(R.layout.element_chat_message))
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(messages[position], position != 0 && messages[position].from.id == messages[position - 1].from.id)
    }

    class MessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: MessageModel, prevContains: Boolean) {
            if (!prevContains) {
                itemView.avatar.visibility = View.VISIBLE
                Glide.with(itemView)
                        .load(model.from.photo)
                        .into(itemView.avatar)
            } else {
                itemView.avatar.visibility = View.INVISIBLE
            }
            with(itemView) {
                if (!prevContains) {
                    name.visibility = View.VISIBLE
                    name.text = "${model.from.firstName} ${model.from.lastName}"
                } else {
                    name.visibility = View.INVISIBLE
                }
                message.text = model.message
            }
        }
    }
}