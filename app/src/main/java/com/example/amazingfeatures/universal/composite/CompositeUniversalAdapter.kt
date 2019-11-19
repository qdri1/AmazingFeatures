package com.example.amazingfeatures.universal.composite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingfeatures.BR
import com.example.amazingfeatures.universal._abstract.UniversalItemInterface

open class CompositeUniversalAdapter<T : UniversalItemInterface>(
    private val itemLayoutId: Int,
    private val checkForViewType: (Any) -> Boolean,
    private val onAnyItemClick: (Int, T, Int) -> Unit = { _, _, _ -> }
) :
    RecyclerView.Adapter<CompositeUniversalAdapter.RecyclerItemViewHolder<T>>() {

    private var items: ArrayList<T> = ArrayList()

    fun setItems(items: ArrayList<T>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, itemLayoutId, parent, false)
        return RecyclerItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerItemViewHolder<T>, position: Int) {
        val item = items[position]
        holder.bind(item)
        item.onClick = { type -> onAnyItemClick(type, item, position) }
    }

    override fun getItemCount(): Int = items.size

    fun isForViewType(item: Any): Boolean {
        return checkForViewType(item)
    }

    class RecyclerItemViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}
