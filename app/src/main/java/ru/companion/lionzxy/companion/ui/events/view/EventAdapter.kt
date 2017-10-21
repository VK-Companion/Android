package ru.companion.lionzxy.companion.ui.events.view

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.element_event.view.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.utils.inflate
import ru.companion.lionzxy.companion.utils.toDay
import ru.companion.lionzxy.companion.utils.toMonthString

class EventAdapter(private var events: List<EventModel>) : RecyclerView.Adapter<EventAdapter.EventHolder>() {
    override fun onBindViewHolder(holder: EventHolder?, position: Int) {
        holder?.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        return EventHolder(parent.inflate(R.layout.element_event))
    }

    class EventHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(event: EventModel) {
            with(itemView) {
                parent_ll.setBackgroundColor(event.color)
                date_number.text = event.date.toDay().toString()
                date_month.text = event.date.toMonthString(itemView.context)
                title.text = event.name
                if (event.distance > 1000) {
                    direction.text = "${Math.round(event.distance.toFloat() / 100) / 10} км"
                } else {
                    direction.text = "${event.distance} м"
                }
            }
            Glide.with(itemView)
                    .asBitmap()
                    .load(event.img_url)
                    .into(itemView.eventImage)
        }
    }

    fun plusEvents(events: List<EventModel>) {
        events.plus(events)
    }
}