package com.example.stackexchangeapp.ui.mainscreen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stackexchangeapp.R
import com.example.stackexchangeapp.ui.mainscreen.fragment.MainScreenFragment
import com.example.stackexchangeapp.ui.userdetails.activity.UserDetailsActivity

class MainActivity : AppCompatActivity(), MainScreenFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startMainScreenFragment()
    }

    private fun startMainScreenFragment() {
        val fragmentManager = supportFragmentManager
        val fragment =
            fragmentManager.findFragmentByTag(MainScreenFragment.TAG_MAIN_SCREEN_FRAGMENT)
        if (fragment == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.main_screen_fragment_container,
                MainScreenFragment.newInstance(),
                MainScreenFragment.TAG_MAIN_SCREEN_FRAGMENT
            ).commit()
        }
    }

    override fun onFragmentInteraction(userId: Int) {
        val intent = Intent(this, UserDetailsActivity::class.java).apply {
            putExtra(USER_ID_INTENT_KEY, userId)
        }
        startActivity(intent)
    }
    
    companion object {
        const val USER_ID_INTENT_KEY = "userId"
    }
}
