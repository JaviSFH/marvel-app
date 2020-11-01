package com.tuppersoft.marvel.features.charactersdetails.comiccover

import androidx.recyclerview.widget.RecyclerView
import com.tuppersoft.domain.customtypealias.OnClickItem
import com.tuppersoft.domain.models.comic.Comic
import com.tuppersoft.marvel.databinding.ComicItemBinding

class ItemComicViewHolder(private val binding: ComicItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(comic: Comic, onClickItem: OnClickItem<Comic>?) {

        binding.comic = comic
        binding.cardCharacter.setOnClickListener {
            onClickItem?.invoke(comic)
        }
    }
}
