package com.tuppersoft.marvel.features.charactersdetails.comiccover

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuppersoft.domain.customtypealias.OnClickItem
import com.tuppersoft.domain.models.comic.Comic
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.extension.inflateDataBinding
import com.tuppersoft.marvel.core.platform.GlobalItem
import com.tuppersoft.marvel.databinding.ShimmerComicItemBinding

class ComicListAdapter : ListAdapter<GlobalItem, RecyclerView.ViewHolder>(ItemCharacterDiffCallback()) {

    var onClickItem: OnClickItem<Comic>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GlobalItem.TypeItem.NORMAL.value -> {
                ItemComicViewHolder(parent.inflateDataBinding(R.layout.comic_item))
            }
            GlobalItem.TypeItem.SHIMMER.value -> {
                ShimmerViewHolder(parent.inflateDataBinding(R.layout.shimmer_comic_item))
            }
            else -> throw IllegalArgumentException("Unknown item type")

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is ItemComicViewHolder -> holder.bind((currentList[position] as ComicItem).comic, onClickItem)
        }
    }

    fun setOnclickItemListener(onClickItem: OnClickItem<Comic>) {
        this.onClickItem = onClickItem
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].typeItem.value
    }

    inner class ShimmerViewHolder(binding: ShimmerComicItemBinding) : RecyclerView.ViewHolder(binding.root)
}

