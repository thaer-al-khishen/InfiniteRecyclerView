package com.example.loopingrecyclerviewapp.utils

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class InfiniteSnapHelper : LinearSnapHelper() {

    /**
     * Returns [RecyclerView.NO_POSITION] so that the snap helper is forced to use the default
     * animation logic.
     */
    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {
        return RecyclerView.NO_POSITION;
    }
}