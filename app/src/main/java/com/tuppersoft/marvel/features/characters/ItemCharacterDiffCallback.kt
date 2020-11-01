package com.tuppersoft.marvel.features.characters

import androidx.recyclerview.widget.DiffUtil
import com.tuppersoft.marvel.core.platform.GlobalItem
import com.tuppersoft.marvel.features.charactersdetails.comiccover.ComicItem

class ItemCharacterDiffCallback : DiffUtil.ItemCallback<GlobalItem>() {

    override fun areItemsTheSame(oldItem: GlobalItem, newItem: GlobalItem): Boolean {
        return (oldItem is ComicItem) && (newItem is CharacterItem) && oldItem.comic.id == newItem.character.id
    }

    override fun areContentsTheSame(oldItem: GlobalItem, newItem: GlobalItem): Boolean {
        return (oldItem is ComicItem) && (newItem is CharacterItem) && oldItem.comic.id == newItem.character.id
    }
}
