package com.example.photooftheday.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.photooftheday.databinding.ActivityRecyclerItemEarthBinding
import com.example.photooftheday.databinding.ActivityRecyclerItemHeaderBinding
import com.example.photooftheday.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter (private val data:MutableList<Data>, private val callbackListener:MyCallback): RecyclerView.Adapter<BaseViewHolder>() {

    fun appendItem(){
        data.add(generateItem())
        notifyItemInserted(itemCount-1)
    }

    private fun generateItem():Data{
        return Data(someText = "Mars")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType){
            TYPE_EARTH -> {
                val bindingViewHolder = ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                EarthViewHolder(bindingViewHolder.root)
            }
            TYPE_HEADER -> {
                val bindingViewHolder =ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                HeaderViewHolder(bindingViewHolder.root)
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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class EarthViewHolder(view: View):BaseViewHolder(view){
        override fun bind(data:Data){
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                descriptionTextView.text = data.someDescription
                wikiImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }
    inner class MarsViewHolder(view: View):BaseViewHolder(view){
        override fun bind(data:Data){
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                marsImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
                addItemImageView.setOnClickListener {
                    addItemToPosition()
                }
                removeItemImageView.setOnClickListener {
                    removeItem()
                }
            }
        }
        private fun addItemToPosition(){
            data.add(layoutPosition,generateItem())
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem(){
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }
    inner class HeaderViewHolder(view: View):BaseViewHolder(view){
        override fun bind(data:Data){
            ActivityRecyclerItemHeaderBinding.bind(itemView).apply {
                header.text = data.someText
                root.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }
}