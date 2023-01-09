package com.r7fx.farmagitechs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GeneralAdapter<T : Any, X : ViewBinding> (
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> X
) {
    private var _areItemsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem ->
            oldItem == newItem
        }

    private var _areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem ->
            oldItem == newItem
        }

    private var _onItemClick: (item: T) -> Unit = {}

    private var _onItemLongClick: (item: T) -> Unit = {}

    private var _onBind: (view: X, item: T) -> Unit = { _, _ -> }

    private inner class ItemCallback : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = _areItemsTheSame(oldItem, newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T) =
            _areContentsTheSame(oldItem, newItem)
    }

    inner class Adapter : ListAdapter<T, Adapter.ViewHolder>(ItemCallback()) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = getItem(position)
            holder.bind(data)
        }

        inner class ViewHolder(private val binding: X) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: T) {
                _onBind(binding, item)
                with(binding.root) {
                    setOnClickListener {
                        _onItemClick(item)
                    }

                    setOnLongClickListener {
                        _onItemLongClick(item)
                        true
                    }
                }
            }
        }
    }

    fun onItemClick(callback: (item: T) -> Unit): GeneralAdapter<T, X> {
        _onItemClick = callback
        return this
    }

    fun onItemLongClick(callback: (item: T) -> Unit): GeneralAdapter<T, X> {
        _onItemLongClick = callback
        return this
    }

    fun onBind(callback: (view: X, data: T) -> Unit): GeneralAdapter<T, X> {
        _onBind = callback
        return this
    }

    fun areItemsTheSame(callback: (oldItem: T, newItem: T) -> Boolean): GeneralAdapter<T, X> {
        _areItemsTheSame = callback
        return this
    }

    fun areContentsTheSame(callback: (oldItem: T, newItem: T) -> Boolean): GeneralAdapter<T, X> {
        _areContentsTheSame = callback
        return this
    }

    fun build() = Adapter()
}