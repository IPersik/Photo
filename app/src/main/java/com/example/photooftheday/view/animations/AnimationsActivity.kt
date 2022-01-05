package com.example.photooftheday.view.animations

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ArcMotion
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
        binding.button.setOnClickListener {
            isDirectionRight = !isDirectionRight
            val params = binding.button.layoutParams as FrameLayout.LayoutParams
            params.gravity = if (isDirectionRight) {
                Gravity.BOTTOM or Gravity.END
            } else {
                Gravity.TOP or Gravity.START
            }

            val transition = ChangeBounds()
            val path = ArcMotion()
            transition.setPathMotion(path)
            transition.duration = 3000
            TransitionManager.beginDelayedTransition(binding.transitionsContainer, transition)

            binding.button.layoutParams = params
        }
    }
}
