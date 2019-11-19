package com.example.amazingfeatures.universal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingfeatures.BR
import com.example.amazingfeatures.universal._abstract.UniversalItemInterface

open class UniversalRecyclerViewAdapter<T : UniversalItemInterface>(
    private var items: ArrayList<T>,
    private val itemLayoutId: Int,
    private val onAnyItemClick: (Int, T, Int) -> Unit
) :
    RecyclerView.Adapter<UniversalRecyclerViewAdapter.RecyclerItemViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, itemLayoutId, parent, false)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder<T>, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnLongClickListener {
            onAnyItemClick(10, item, position)
            true
        }

        item.onClick = {
                type -> onAnyItemClick(type, item, position)
        }
    }

    override fun getItemCount(): Int = items.size

    class RecyclerItemViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}
