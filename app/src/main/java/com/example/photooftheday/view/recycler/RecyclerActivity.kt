package com.example.photooftheday.view.recycler


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.photooftheday.databinding.ActivityRecyclerBinding

class RecyclerActivity:AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            Data("Earth",type= TYPE_EARTH) to false,
            Data("Mars", "", type = TYPE_MARS) to false/*,
            Data("Earth",type= TYPE_EARTH),
            Data("Mars", "",type= TYPE_MARS),
            Data("Earth",type= TYPE_EARTH),
            Data("Earth",type= TYPE_EARTH),
            Data("Earth",type= TYPE_EARTH),
            Data("Mars", null,type= TYPE_MARS)*/
        )
        data.add(0,Data("Заголовок",type= TYPE_HEADER) to false)

        val lat  = 55
        val lon  = 37
        val coordinate1 = lat to lon
        val coordinate2 = Pair(lat,lon)
        coordinate1.first
        coordinate1.second
        val coordinate3d = Triple(1,2,3)
        coordinate3d.first
        coordinate3d.second
        coordinate3d.third

        val adapter = RecyclerActivityAdapter(data,
            object : MyCallback {
                override fun onClick(position: Int) {
                    Toast.makeText(
                        this@RecyclerActivity,
                        "РАБОТАЕТ ${data[position].first.someText} ${data[position].first.someDescription}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
        }
    }
}