package com.example.simpletodolist.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerviewUtil {

    class HorizontalItemDecoration(private val horizontalSpaceHeight: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.right = horizontalSpaceHeight
            outRect.left = horizontalSpaceHeight
        }
    }

    class RightSpaceItemDecoration(private val HorizonSpaceWidth: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.right = HorizonSpaceWidth
        }
    }

    class TopSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.top = verticalSpaceHeight
        }
    }
}