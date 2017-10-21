package ru.companion.lionzxy.companion.ui.profile.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_user_info.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.data.models.ActionModel
import ru.companion.lionzxy.companion.data.models.UserProfile
import ru.companion.lionzxy.companion.ui.profile.presenter.ProfilePresenter
import ru.companion.lionzxy.companion.utils.inflate

class ProfileFragment : MvpAppCompatFragment(), ProfileView {
    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_user_info)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.loadUser()
    }

    override fun onLoadUser(userData: UserProfile, actions: List<ActionModel>?) {
        val adapter = RecyclerViewAdapter(userData)
        adapter.setContent(userData, actions)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}