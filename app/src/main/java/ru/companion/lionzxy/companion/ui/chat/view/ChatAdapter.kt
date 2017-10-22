package ru.companion.lionzxy.companion.ui.chat.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.element_chat_message.view.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.MessageModel
import ru.companion.lionzxy.companion.utils.inflate

class ChatAdapter(private val messages: List<MessageModel>,
                  private var listener: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount() = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            MessageHolder(parent.inflate(R.layout.element_chat_message))
        } else {
            PayHolder(parent.inflate(R.layout.element_payment))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MessageHolder) {
            holder.bind(messages[position], position != 0 && getItemViewType(position - 1) != 1 && messages[position].from.firstName == messages[position - 1].from.firstName)
        } else if(holder is PayHolder) {
            holder.bind(listener)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (messages[position].attachment == "pay") 1 else 0
    }

    class MessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: MessageModel, prevContains: Boolean) {
            if (!prevContains) {
                itemView.avatar.visibility = View.VISIBLE
                Glide.with(itemView)
                        .load(model.from.photo)
                        .apply(RequestOptions.circleCropTransform())
                        .into(itemView.avatar)
            } else {
                itemView.avatar.visibility = View.GONE
            }
            with(itemView) {
                if (!prevContains) {
                    name.visibility = View.VISIBLE
                    name.text = "${model.from.firstName} ${model.from.lastName}"
                } else {
                    name.visibility = View.GONE
                }
                message.text = model.message
            }
        }
    }

    class PayHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listener: () -> Unit) {
            itemView.setOnClickListener({ listener() })
        }
    }
}