package ru.companion.lionzxy.companion.ui.dialogs.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_user_info.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.ui.dialogs.presenter.DialogPresenter
import ru.companion.lionzxy.companion.utils.inflate

class DialogFragment : MvpAppCompatFragment(), DialogView {

    @InjectPresenter
    lateinit var presenter: DialogPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragments_dialogs)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadDialogs()
        toolbar.title = getString(R.string.dialog_tittle)
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun onLoadDialogs(dialogs: List<DialogModel>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        recyclerView.adapter = DialogAdapter(dialogs, {
            presenter.onDialogClick(it)
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}