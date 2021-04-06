package com.example.loopingrecyclerviewapp

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loopingrecyclerviewapp.utils.CenterZoomLayoutManager
import com.example.loopingrecyclerviewapp.utils.InfiniteSnapHelper
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: CenterZoomLayoutManager
    private  var myDataset: ArrayList<String> = ArrayList()
    private val snapHelper = InfiniteSnapHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDataset.add("First")
        myDataset.add("Second")
        myDataset.add("Third")
        viewManager = CenterZoomLayoutManager(this, 0.90f, 0.2f)
        val width = applicationContext.resources.displayMetrics.widthPixels
        val denominator = applicationContext.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
        val widthInDp = width / denominator
        val offset = width/4
        //(Individual item width)/ 2
        viewAdapter = LoopingAdapter(myDataset, width / 2)
        //Number of views to display in the middle + 1
        recyclerView = findViewById<RecyclerView>(R.id.loopingRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        snapHelper.attachToRecyclerView(recyclerView)
        viewManager.scrollToPositionWithOffset(Integer.MAX_VALUE/2, offset)
        //N * Offset with N being the number of views fully visible at the start
    }
}
