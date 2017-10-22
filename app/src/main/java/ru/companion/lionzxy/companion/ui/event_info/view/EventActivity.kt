package ru.companion.lionzxy.companion.ui.event_info.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_event.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.data.models.EventUser
import ru.companion.lionzxy.companion.ui.event_info.presenter.EventPresenter

class EventActivity : MvpAppCompatActivity(), EventView {
    companion object {
        const val EXTRA_EVENT = "event"

        fun getIntent(context: Context, model: EventModel): Intent {
            val intent = Intent(context, EventActivity::class.java)
            intent.putExtra(EXTRA_EVENT, model)
            return intent
        }
    }

    @InjectPresenter
    lateinit var presenter: EventPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        collapsing_toolbar.title = getEvent().name
        Glide.with(this)
                .load(getEvent().img_url)
                .into(image_shot)
        titleText.text = getEvent().name
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    fun getEvent() = intent.getSerializableExtra(EXTRA_EVENT) as EventModel

    override fun loadUser(users: List<EventUser>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        recyclerView.adapter = EventUserAdapter(users, {
            presenter.onInvite(it)
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}