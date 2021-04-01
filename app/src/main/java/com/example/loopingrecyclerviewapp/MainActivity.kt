package com.example.loopingrecyclerviewapp

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loopingrecyclerviewapp.utils.InfiniteSnapHelper
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: LinearLayoutManager
    private  var myDataset: ArrayList<String> = ArrayList()
    private val snapHelper = InfiniteSnapHelper()
    private var excessOffset by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDataset.add("First")
        myDataset.add("Second")
        myDataset.add("Third")

        viewManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )

        val width = applicationContext.resources.displayMetrics.widthPixels
        val denominator = applicationContext.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
        val widthInDp = width / denominator

        val offset = width/4
        //Number of views to be displayed at the start + 2

        when {
            width > 1400 -> excessOffset = 50
            width in 1001..1399 -> excessOffset = 45
            width in 751..999 -> excessOffset = 38
            width in 501..749 -> excessOffset = 31
            width < 500 -> excessOffset = 25
        }


        viewAdapter = LoopingAdapter(myDataset, width / 2)

        recyclerView = findViewById<RecyclerView>(R.id.loopingRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        snapHelper.attachToRecyclerView(recyclerView)

        viewManager.scrollToPositionWithOffset(1, offset - excessOffset)
        //N * Offset with N being the number of views fully visible at the start


    }

}