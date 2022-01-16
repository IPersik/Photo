package com.example.photooftheday.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.photooftheday.R
import com.example.photooftheday.view.api.SystemFragment
import com.example.photooftheday.view.chips.SettingsFragment
import com.example.photooftheday.view.constraint.ConstraintFragment
import com.example.photooftheday.view.motion.MotionFragment
import com.example.photooftheday.view.picture.BottomNavigationDrawerFragment
import com.example.photooftheday.view.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTheme(R.style.Theme_PhotoOfTheDay)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            /*supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()*/

            /*supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit() */

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
            /* supportFragmentManager.beginTransaction()
                 .replace(R.id.container, SettingsFragment.newInstance()).commit()*/
        }
    }
    private fun isConnecton(): Boolean {
        return true
    }

}