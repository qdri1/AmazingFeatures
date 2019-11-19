package com.example.amazingfeatures.universal.composite

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingfeatures.universal._abstract.UniversalItemInterface

import java.util.ArrayList

open class CompositeDelegateAdapter<T : UniversalItemInterface>(
    adapterList: SparseArray<CompositeUniversalAdapter<*>>
) : RecyclerView.Adapter<CompositeUniversalAdapter.RecyclerItemViewHolder<T>>() {

    companion object {
        private const val FIRST_VIEW_TYPE = 0
    }

    private val data: ArrayList<T> = ArrayList()
    private val adapterList = adapterList as SparseArray<CompositeUniversalAdapter<T>>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompositeUniversalAdapter.RecyclerItemViewHolder<T> {
        return adapterList.get(viewType).onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: CompositeUniversalAdapter.RecyclerItemViewHolder<T>,
        position: Int
    ) {
        val delegateAdapter = adapterList.get(getItemViewType(position))
        if (delegateAdapter != null) {
            delegateAdapter.setItems(data)
            delegateAdapter.onBindViewHolder(holder, position)
        } else {
            throw NullPointerException("can not find adapter for position $position")
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        for (i in FIRST_VIEW_TYPE until adapterList.size()) {
            if (adapterList.valueAt(i).isForViewType(data[position])) {
                return adapterList.keyAt(i)
            }
        }
        throw NullPointerException("Can not get viewType for position $position")
    }

    fun swapData(newData: List<T>) {
        data.run {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }
}