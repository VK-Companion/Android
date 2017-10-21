package ru.companion.lionzxy.companion.ui.events.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_user_info.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.ui.events.presenter.EventPresenter
import ru.companion.lionzxy.companion.utils.inflate

class EventFragment : MvpAppCompatFragment(), EventView {
    @InjectPresenter
    lateinit var presenter: EventPresenter

    var adapter: EventAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragment_events)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadEvent()
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun plusEvents(events: List<EventModel>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        if(adapter == null){
            adapter = EventAdapter(events)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        } else {
            adapter!!.plusEvents(events)
        }
    }

}