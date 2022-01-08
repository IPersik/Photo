package com.example.photooftheday.view.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.photooftheday.R
import com.example.photooftheday.databinding.ActivityAnimationsBinding

private const val duration = 1000L
class AnimationsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAnimationsBinding

    var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            val constraintSetStart = ConstraintSet()
            constraintSetStart.clone(this,R.layout.activity_animations)
            constraintSetStart.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.END)
            constraintSetStart.connect(R.id.description,ConstraintSet.TOP,R.id.title,ConstraintSet.BOTTOM)
            /*val constraintSetEnd = ConstraintSet()
            constraintSetEnd.clone(this,R.layout.activity_animations_end)*/

            val transition = ChangeBounds()
            transition.duration = 1000
            transition.interpolator = AnticipateOvershootInterpolator(1f)
            TransitionManager.beginDelayedTransition(binding.root,transition)
            constraintSetStart.applyTo(binding.constraintContainer)
        }
    }
}
