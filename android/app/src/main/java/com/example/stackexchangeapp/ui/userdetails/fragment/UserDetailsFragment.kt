package com.example.stackexchangeapp.ui.userdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.business.dataviewmodel.UserDetailsView
import com.example.stackexchangeapp.ui.mainscreen.activity.MainActivity.Companion.USER_ID_INTENT_KEY
import com.example.stackexchangeapp.ui.userdetails.UserDetailsStateView
import com.example.stackexchangeapp.ui.userdetails.viewmodel.UserDetailsViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserDetailsFragment : Fragment() {

    private val viewModel: UserDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = arguments?.getInt(USER_ID_INTENT_KEY)
        viewModel.userDetailsState.observe(viewLifecycleOwner, Observer {
            renderView(it)
        })
        userId?.let { viewModel.getUserDetails(it) }
    }

    private fun renderView(userDetailsStateView: UserDetailsStateView) {
        when (userDetailsStateView) {
            UserDetailsStateView.ShowLoading -> showProgressBar()
            UserDetailsStateView.HideLoading -> hideProgressBar()
            is UserDetailsStateView.ShowData -> setUserData(userDetailsStateView.data)
            UserDetailsStateView.ShowError -> Toast.makeText(
                context,
                getString(R.string.error_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showProgressBar() {
        pb_user_details.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pb_user_details.visibility = View.GONE
    }

    private fun setUserData(userDetailsView: UserDetailsView) {
        Glide.with(this).load(userDetailsView.imageUrl).into(iv_profile_image)
        tv_username.text = userDetailsView.userName
        tv_reputation.text = userDetailsView.reputation
        tv_bronze_badge.text = userDetailsView.badgesBronze
        tv_silver_badge.text = userDetailsView.badgesSilver
        tv_gold_badge.text = userDetailsView.badgesGold
        tv_location.text = userDetailsView.location
        tv_age.text = userDetailsView.age
        tv_creation_date.text = userDetailsView.creationDate
    }

    companion object {
        const val TAG_USER_DETAILS_FRAGMENT = "userDetailsFragment"
        fun newInstance(bundle: Bundle? = null): UserDetailsFragment {
            val userDetailsFragment = UserDetailsFragment()
            if (bundle != null) {
                userDetailsFragment.arguments = bundle
            }
            return userDetailsFragment
        }
    }
}