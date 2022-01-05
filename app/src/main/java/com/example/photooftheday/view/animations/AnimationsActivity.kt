package com.example.photooftheday.view.animations

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.photooftheday.databinding.ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isDirectionRight = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titles: MutableList<String> = ArrayList()
        for (i in 0..4) {
            titles.add("Item $i")
        }

        binding.button.setOnClickListener {
            val cb = ChangeBounds()
            cb.duration = 2000
            TransitionManager.beginDelayedTransition(binding.transitionsContainer, cb)
            titles.shuffle()

            binding.transitionsContainer.removeAllViews()
            titles.forEach {
                binding.transitionsContainer.addView(TextView(this).apply {
                    text = it
                    ViewCompat.setTransitionName(this, it)
                    gravity = Gravity.CENTER_HORIZONTAL
                })
            }
        }
    }
}
