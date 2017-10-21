package ru.companion.lionzxy.companion.ui.profile.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.element_action.view.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.ActionModel
import ru.companion.lionzxy.companion.data.models.UserProfile
import ru.companion.lionzxy.companion.utils.inflate

class RecyclerViewAdapter(private var user: UserProfile) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var actions: List<ActionModel>? = null

    companion object {
        const val TYPE_PROFILE = 1
        const val TYPE_NOTFOUND = 2
        const val TYPE_ACTION = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TYPE_PROFILE -> ProfileViewHolder(parent.inflate(R.layout.element_profile_head))
                TYPE_NOTFOUND -> NotfoundViewHolder(parent.inflate(R.layout.element_actions_notfound))
                TYPE_ACTION -> ActionViewHolder(parent.inflate(R.layout.element_action))
                else -> ActionViewHolder(parent)
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is ProfileViewHolder -> holder.bind(user)
            is ActionViewHolder -> actions?.get(position - 1)?.let { holder.bind(it) }
            is NotfoundViewHolder -> {
            } // Do nothing
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_PROFILE
        } else if (position == 1 && actions == null) {
            TYPE_NOTFOUND
        } else {
            TYPE_ACTION
        }
    }

    override fun getItemCount() = (actions?.size ?: 1) + 1

    fun setContent(userData: UserProfile, actions: List<ActionModel>?) {
        this.user = userData
        this.actions = actions
        notifyDataSetChanged()
    }

    class ActionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(action: ActionModel) {
            //TODO action
        }
    }

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: UserProfile) {
            //TODO avatar
            with(itemView) {
                profile_name.text = "${user.firstName} ${user.lastName}"
                status.text = user.status
            }
        }
    }

    class NotfoundViewHolder(view: View) : RecyclerView.ViewHolder(view)

}