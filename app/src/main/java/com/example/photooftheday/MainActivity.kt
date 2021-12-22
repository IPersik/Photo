package com.example.photooftheday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photooftheday.view.picture.PictureOfTheDayFragment
import com.example.photooftheday.view.picture.chips.SettingsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingsFragment.newInstance()).commit()
        setContentView(R.layout.activity_main)
            //if(savedInstanceState==null){
            //supportFragmentManager.beginTransaction().replace(R.id.container, PictureOfTheDayFragment.newInstance()).commit()
        }
    }
    private fun isConnecton(): Boolean {
        return true
    }
}