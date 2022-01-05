package com.example.photooftheday.view.animations

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import com.example.photooftheday.databinding.ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isTextViewVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            isTextViewVisible = !isTextViewVisible
            var transition = TransitionSet()// исчезновение
            transition.addTransition(Fade())
            transition.addTransition(ChangeBounds())
            //transition.ordering = TransitionSet.ORDERING_SEQUENTIAL // указали последовательное выполнение
            transition.duration = 5000
            TransitionManager.beginDelayedTransition(binding.transitionsContainer,transition)
            binding.text.visibility = if(isTextViewVisible) View.VISIBLE else View.GONE
        }
    }
}