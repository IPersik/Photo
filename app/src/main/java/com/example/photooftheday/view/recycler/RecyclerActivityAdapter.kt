package com.example.photooftheday.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.photooftheday.databinding.ActivityRecyclerItemEarthBinding
import com.example.photooftheday.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter (private val data:List<Data>, private val callbackListener:MyCallback): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            TYPE_EARTH -> {
                val bindingViewHolder = ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                EarthViewHolder(bindingViewHolder.root)
            }
            else -> {
                val bindingViewHolder = ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                MarsViewHolder(bindingViewHolder.root)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type //
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItemViewType(position)){
            TYPE_EARTH -> {
                (holder as EarthViewHolder).bind(data[position])
            }
            else -> {
                (holder as MarsViewHolder).bind(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class EarthViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bind(data:Data){
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                descriptionTextView.text = data.someDescription
                wikiImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }
    inner class MarsViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bind(data:Data){
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                marsImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }
}