package com.emv.kotlinmultiplatformmvi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.emv.datalayer.models.Items
import com.emv.kotlinmultiplatformmvi.R
import com.emv.kotlinmultiplatformmvi.databinding.ItemListBinding

class ChannelsListAdapter(val requestManager : RequestManager) : RecyclerView.Adapter<ChannelsViewHolder>() {
    private lateinit var items: MutableList<Items>

    fun updateProgress() {
        notifyItemRangeChanged(0, itemCount, true)
    }

    fun updateItems(items: List<Items> ?) {
        if (items != null) {
            this.items = items.toMutableList()
        } else if (this::items.isInitialized) {
            this.items.clear()
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ChannelsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: ChannelsViewHolder, position: Int) {
        holder.mBinding.item = items[position]
        requestManager.load(items[position].media.m)
            .apply(RequestOptions()
            .fitCenter())
            .into(holder.mBinding.image)
        holder.setIsRecyclable(false)
    }
}

class ChannelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal val mBinding: ItemListBinding

    init {
        mBinding = ItemListBinding.bind(itemView)
    }
}