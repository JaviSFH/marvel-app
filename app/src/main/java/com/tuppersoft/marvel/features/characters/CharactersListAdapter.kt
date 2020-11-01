package com.tuppersoft.marvel.features.characters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuppersoft.domain.customtypealias.OnClickItemBinding
import com.tuppersoft.domain.models.character.Characters
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.extension.inflateDataBinding
import com.tuppersoft.marvel.core.platform.GlobalItem
import com.tuppersoft.marvel.databinding.CharacterItemBinding
import com.tuppersoft.marvel.databinding.ShimmerCharacterItemBinding

class CharactersListAdapter : ListAdapter<GlobalItem, RecyclerView.ViewHolder>(ItemCharacterDiffCallback()) {

    var onClickItem: OnClickItemBinding<Characters, CharacterItemBinding>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GlobalItem.TypeItem.NORMAL.value -> {
                ItemCharacterViewHolder(parent.inflateDataBinding(R.layout.character_item))
            }
            GlobalItem.TypeItem.SHIMMER.value -> {
                ShimmerViewHolder(parent.inflateDataBinding(R.layout.shimmer_character_item))
            }
            else -> throw IllegalArgumentException("Unknown item type")

        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].typeItem.value
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemCharacterViewHolder -> holder.bind((currentList[position] as CharacterItem).character, onClickItem)
        }
    }

    fun setOnclickItemListener(onClickItem: OnClickItemBinding<Characters, CharacterItemBinding>) {
        this.onClickItem = onClickItem
    }

    inner class ShimmerViewHolder(binding: ShimmerCharacterItemBinding) : RecyclerView.ViewHolder(binding.root)
}

