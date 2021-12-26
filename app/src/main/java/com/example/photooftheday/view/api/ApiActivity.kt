package com.example.photooftheday.view.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photooftheday.R
import com.example.photooftheday.databinding.ActivityApiBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ApiActivity :AppCompatActivity() {
    lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout,binding.viewPager, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            }
        }).attach()
        setCustomTabs()
    }


    private fun setCustomTabs() {
        binding.tabLayout.getTabAt(0)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tabitem_earth, null)
        binding.tabLayout.getTabAt(1)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tabitem_mars, null)
        binding.tabLayout.getTabAt(2)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tabitem_system, null)
    }

}