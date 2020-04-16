package com.example.stackexchangeapp.ui.userdetails.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.ui.mainscreen.activity.MainActivity.Companion.USER_ID_INTENT_KEY
import com.example.stackexchangeapp.ui.userdetails.fragment.UserDetailsFragment

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val userId = intent.getIntExtra(USER_ID_INTENT_KEY, -1)
        val bundle = Bundle().apply {
            putInt(USER_ID_INTENT_KEY, userId)
        }
        startUserDetailsFragment(bundle)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
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