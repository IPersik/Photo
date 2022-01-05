package com.example.photooftheday.view.animations

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.photooftheday.databinding.ActivityAnimationsBinding

import com.example.photooftheday.R

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isTextViewVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = Adapter()
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_animations_recycler_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val explodeTransition = Explode()
                val button = it as Button
                val fadeTransition = Fade()
                val setTransition = TransitionSet()
                explodeTransition.duration = 5000
                fadeTransition.duration = 5000
                explodeTransition.excludeTarget(button, true)
                explodeTransition.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        val rect = Rect(
                            button.x.toInt(),
                            button.y.toInt(),
                            button.x.toInt(),
                            button.y.toInt()
                        )
                        //val rect = Rect()
                        //button.getGlobalVisibleRect(rect) // почти то же самое
                        return rect
                    }
                }

                setTransition.addTransition(fadeTransition)
                setTransition.addTransition(explodeTransition)
                TransitionManager.beginDelayedTransition(binding.recyclerView, setTransition)
                binding.recyclerView.adapter = null
            }
        }

        override fun getItemCount(): Int {
            return 32
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
