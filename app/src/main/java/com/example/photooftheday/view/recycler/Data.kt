package com.example.photooftheday.view.recycler

const val TYPE_EARTH =0
const val TYPE_MARS =1
data class Data(val someText: String = "Text", val someDescription: String? = "Description",val type:Int=TYPE_MARS)

