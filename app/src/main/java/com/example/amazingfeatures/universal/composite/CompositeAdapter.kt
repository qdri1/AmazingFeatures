package com.example.amazingfeatures.universal.composite

import android.util.SparseArray
import com.example.amazingfeatures.universal._abstract.UniversalItemInterface


open class CompositeAdapter(
    adapterList: SparseArray<CompositeUniversalAdapter<*>>
) : CompositeDelegateAdapter<UniversalItemInterface>(adapterList) {

    class Builder {

        private var count: Int = 0
        private val adapterList: SparseArray<CompositeUniversalAdapter<*>> = SparseArray()

        fun add(delegateAdapter: CompositeUniversalAdapter<out UniversalItemInterface>): Builder {
            adapterList.put(count++, delegateAdapter)
            return this
        }

        fun build(): CompositeAdapter {
            return CompositeAdapter(adapterList)
        }
    }
}