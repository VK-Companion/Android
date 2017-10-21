package ru.companion.lionzxy.companion.ui.events.view

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.fragment_user_info.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.ui.events.presenter.EventPresenter
import ru.companion.lionzxy.companion.utils.inflate
import timber.log.Timber

class EventFragment : MvpAppCompatFragment(), EventView {
    @InjectPresenter
    lateinit var presenter: EventPresenter
    private lateinit var rxPermissions: RxPermissions
    var adapter: EventAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragment_events)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadEvent()
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        rxPermissions = RxPermissions(activity)
        openPermissionRequest()
    }

    override fun plusEvents(events: List<EventModel>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        if (adapter == null) {
            adapter = EventAdapter(events)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        } else {
            adapter!!.plusEvents(events)
        }
    }

    private fun openPermissionDialog() {
        val dialog = AlertDialog.Builder(activity)
                .setMessage(R.string.permission_not_found)
                .setPositiveButton(android.R.string.ok, { dialog, _ ->
                    openPermissionRequest()
                    dialog.dismiss()
                })
                .setNegativeButton(android.R.string.no, { dialog, _ -> dialog.dismiss() }).create()
        dialog.show()
    }

    private fun openPermissionRequest() {
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({
                    if (it) {
                        presenter.subscribeToLocation(context)
                    } else {
                        openPermissionDialog()
                    }
                }, Timber::e)
    }

}