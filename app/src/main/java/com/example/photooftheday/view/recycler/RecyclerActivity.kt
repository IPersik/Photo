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
            Data("Earth",type= TYPE_EARTH)/*,
            Data("Earth",type= TYPE_EARTH),
            Data("Mars", "",type= TYPE_MARS),
            Data("Earth",type= TYPE_EARTH),
            Data("Earth",type= TYPE_EARTH),
            Data("Earth",type= TYPE_EARTH),
            Data("Mars", null,type= TYPE_MARS)*/
        )
        data.add(0,Data("Заголовок",type= TYPE_HEADER))


        data.add(0, Data("Заголовок", type = TYPE_HEADER))


        val adapter = RecyclerActivityAdapter(data,
            object : MyCallback {
                override fun onClick(position: Int) {
                    Toast.makeText(
                        this@RecyclerActivity,
                        "РАБОТАЕТ ${data[position].someText} ${data[position].someDescription}",
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