package ru.companion.lionzxy.companion.ui.event_info.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.element_user_event.view.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.EventUser
import ru.companion.lionzxy.companion.utils.inflate

class EventUserAdapter(private val users: List<EventUser>,
                       private var listener: (model: EventUser) -> Unit) : RecyclerView.Adapter<EventUserAdapter.UserHolder>() {
    override fun onBindViewHolder(holder: UserHolder?, position: Int) {
        holder?.bind(users[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(parent.inflate(R.layout.element_user_event))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: EventUser, listener: (model: EventUser) -> Unit) {
            Glide.with(itemView)
                    .load(user.photo)
                    .apply(RequestOptions.circleCropTransform())
                    .into(itemView.ava)
            with(itemView) {
                invite.setOnClickListener({ listener(user) })
                profile_name.text = "${user.firstName} ${user.lastName}"
                status.text = user.reason
            }
        }
    }
}