package com.example.stackexchangeapp.ui.userdetails.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.ui.mainscreen.activity.MainActivity.Companion.USER_ID_INTENT_KEY
import com.example.stackexchangeapp.ui.userdetails.fragment.UserDetailsFragment

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val userId = intent.getIntExtra(USER_ID_INTENT_KEY, -1)
        val bundle = Bundle().apply {
            putInt(USER_ID_INTENT_KEY, userId)
        }
        startUserDetailsFragment(bundle)
    }

    private fun startUserDetailsFragment(bundle: Bundle) {
        val fragmentManager = supportFragmentManager
        val fragment =
            fragmentManager.findFragmentByTag(UserDetailsFragment.TAG_USER_DETAILS_FRAGMENT)
        if (fragment == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.user_details_fragment_container,
                UserDetailsFragment.newInstance(bundle),
                UserDetailsFragment.TAG_USER_DETAILS_FRAGMENT
            ).commit()
        }
    }
}