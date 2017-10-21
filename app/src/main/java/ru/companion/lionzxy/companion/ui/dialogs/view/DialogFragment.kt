package ru.companion.lionzxy.companion.ui.dialogs.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.togezzer.android.utils.navigation.BaseNavigator
import com.togezzer.android.utils.navigation.CompanionRouter
import kotlinx.android.synthetic.main.fragment_user_info.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.ui.dialogs.presenter.DialogPresenter
import ru.companion.lionzxy.companion.ui.vk.view.LoginVkActivity
import ru.companion.lionzxy.companion.utils.inflate
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class DialogFragment : MvpAppCompatFragment(), DialogView {

    @InjectPresenter
    lateinit var presenter: DialogPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            container?.inflate(R.layout.fragments_dialogs)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadDialogs()
        toolbar.title = getString(R.string.dialog_tittle)
    }

    override fun onLoadDialogs(dialogs: List<DialogModel>) {
        recyclerView.adapter = DialogAdapter(dialogs, {
            presenter.onDialogClick(it)
        })
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}