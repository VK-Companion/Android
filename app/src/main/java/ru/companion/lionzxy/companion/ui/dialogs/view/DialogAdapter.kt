package ru.companion.lionzxy.companion.ui.dialogs.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.element_dialog.view.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.utils.inflate

class DialogAdapter(private val dialogs: List<DialogModel>,
                    private var listener: (model: DialogModel) -> Unit) : RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        return DialogViewHolder(parent.inflate(R.layout.element_dialog))
    }

    override fun onBindViewHolder(holder: DialogViewHolder?, position: Int) {
        holder?.bind(dialogs[position], listener)
    }

    override fun getItemCount() = dialogs.size

    class DialogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(dialog: DialogModel, listener: (model: DialogModel) -> Unit) {
            with(itemView) {
                message.text = dialog.messsage
                name.text = "${dialog.user.firstName} ${dialog.user.lastName}"
                Glide.with(itemView)
                        .load(dialog.user.photo)
                        .apply(RequestOptions.circleCropTransform())
                        .into(avatar)
                setOnClickListener { listener(dialog) }
            }
        }
    }
}