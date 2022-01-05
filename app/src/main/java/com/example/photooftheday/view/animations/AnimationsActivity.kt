package com.example.photooftheday.view.animations

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*

import com.example.photooftheday.R
import com.example.photooftheday.databinding.ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isExpand= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setOnClickListener {
            isExpand = !isExpand

            val params = binding.imageView.layoutParams as FrameLayout.LayoutParams

            val transitionSet = TransitionSet()
            val transitionCB = ChangeBounds()
            val transitionImage = ChangeImageTransform()
            transitionCB.duration = 2000
            transitionImage.duration = 2000
            transitionSet.addTransition(transitionCB)
            transitionSet.addTransition(transitionImage)
            TransitionManager.beginDelayedTransition(binding.container, transitionSet)

            if (isExpand) {
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
            } else {
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                params.height = FrameLayout.LayoutParams.WRAP_CONTENT
            }

            binding.imageView.layoutParams = params
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
