package com.example.loopingrecyclerviewapp.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

internal class CenterZoomLayoutManager (
    context: Context,
    private val mShrinkDistance: Float,
    private val mShrinkAmount: Float
) : LinearLayoutManager(context, HORIZONTAL, false) {
    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChildren()
    }
    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return if (orientation == HORIZONTAL) {
            super.scrollHorizontallyBy(dx, recycler, state).also { scaleChildren() }
        } else {
            0
        }
    }
    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = mShrinkDistance * midpoint
        for (i in 0 until childCount) {
            val child = getChildAt(i) as View
            val d = d1.coerceAtMost(
                abs(
                    midpoint - (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                )
            )
            val scale = 1f - mShrinkAmount * d / d1
            child.scaleX = scale
            child.scaleY = scale
        }
    }
}
