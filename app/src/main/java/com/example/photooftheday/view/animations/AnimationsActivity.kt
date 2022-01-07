package com.example.photooftheday.view.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.photooftheday.databinding.ActivityAnimationsBinding

private const val duration = 1000L
class AnimationsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAnimationsBinding

    var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.transparentBackground.alpha =0f
        binding.optionOneContainer.alpha =0f
        binding.optionOneContainer.isClickable = false
        binding.optionTwoContainer.alpha =0f
        binding.optionTwoContainer.isClickable = false

        binding.fab.setOnClickListener {
            if(isExpanded){
                isExpanded = false
                ObjectAnimator.ofFloat(binding.plusImageview,"rotation",315f,0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer,"translationY",0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer,"translationY",0f)
                    .setDuration(duration).start()
                binding.optionOneContainer.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionOneContainer.isClickable = false
                        }
                    })
                binding.optionTwoContainer.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionTwoContainer.isClickable = false
                        }
                    })
                binding.transparentBackground.animate()
                    .alpha(0f)
                    .setDuration(duration)
            }else{
                isExpanded = true




                ObjectAnimator.ofFloat(binding.plusImageview,"rotation",0f,315f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer,"translationY",-300f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer,"translationY",-150f)
                    .setDuration(duration).start()
                binding.optionOneContainer.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionOneContainer.isClickable = true
                        }
                    })
                binding.optionTwoContainer.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionTwoContainer.isClickable = true
                        }
                    })

                binding.transparentBackground.animate()
                    .alpha(0.4f)
                    .setDuration(0)
            }
        }
    }
}
