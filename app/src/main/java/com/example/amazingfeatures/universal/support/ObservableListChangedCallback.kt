package com.example.amazingfeatures.universal.support

import android.util.Log
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

open class ObservableListChangedCallback<T>(private val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>? = null) :
    ObservableList.OnListChangedCallback<ObservableList<T>>() {
    override fun onChanged(sender: ObservableList<T>?) {
        Log.v("ListCallback", "onChanged")
        adapter?.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        Log.v("ListCallback", "onItemRangeRemoved ($positionStart, $itemCount) - adapter $adapter")
        adapter?.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        Log.v("ListCallback", "onItemRangeMoved")
        adapter?.notifyItemRangeChanged(fromPosition, itemCount)
    }

    override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        Log.v("ListCallback", "onItemRangeInserted ($positionStart, $itemCount) - adapter $adapter")
        adapter?.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        Log.v("ListCallback", "onItemRangeChanged")
        adapter?.notifyItemRangeChanged(positionStart, itemCount)
    }
}