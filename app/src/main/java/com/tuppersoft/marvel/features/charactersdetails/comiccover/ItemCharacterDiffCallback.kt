package com.tuppersoft.marvel.features.charactersdetails.comiccover

import androidx.recyclerview.widget.DiffUtil
import com.tuppersoft.marvel.core.platform.GlobalItem

class ItemCharacterDiffCallback : DiffUtil.ItemCallback<GlobalItem>() {

    override fun areItemsTheSame(oldItem: GlobalItem, newItem: GlobalItem): Boolean {
        return (oldItem is ComicItem) && (newItem is ComicItem) && oldItem.comic.id == newItem.comic.id
    }

    override fun areContentsTheSame(oldItem: GlobalItem, newItem: GlobalItem): Boolean {
        return (oldItem is ComicItem) && (newItem is ComicItem) && oldItem.comic == newItem.comic
    }
}
