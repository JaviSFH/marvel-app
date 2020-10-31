package com.tuppersoft.marvel.features.characters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuppersoft.domain.customtypealias.OnClickItemBinding
import com.tuppersoft.domain.models.Characters
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.extension.inflateDataBinding
import com.tuppersoft.marvel.databinding.CharacterItemBinding
import com.tuppersoft.marvel.features.characters.CharactersListAdapter.ItemCharacterViewHolder
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment.Companion

class CharactersListAdapter : ListAdapter<Characters, ItemCharacterViewHolder>(ItemCharacterDiffCallback()) {

    var onClickItem: OnClickItemBinding<Characters, CharacterItemBinding>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCharacterViewHolder {
        return ItemCharacterViewHolder(parent.inflateDataBinding(R.layout.character_item))
    }

    override fun onBindViewHolder(holder: ItemCharacterViewHolder, position: Int) {
        holder.bind(currentList[position], onClickItem)
    }

    fun setOnclickItemListener(onClickItem: OnClickItemBinding<Characters, CharacterItemBinding>) {
        this.onClickItem = onClickItem
    }

    inner class ItemCharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characters: Characters, onClickItem: OnClickItemBinding<Characters, CharacterItemBinding>?) {

            binding.character = characters
            setTransitionIds(characters)
            binding.cardCharacter.setOnClickListener {
                onClickItem?.invoke(characters, binding)
            }
        }

        private fun setTransitionIds(item: Characters?) {

            item?.let {
                binding.imageCharacter.transitionName = item.id.toString() + Companion.TRANSITION_PHOTO
                binding.nameCharacter.transitionName = item.id.toString() + Companion.TRANSITION_NAME
            }

            /*  item?.let {
                  ViewCompat.setTransitionName(
                      binding.imageCharacter,
                      item.id.toString() + CharacterDetailFragment.TRANSITION_PHOTO
                  )
                  ViewCompat.setTransitionName(
                      binding.nameCharacter,
                      item.id.toString() + CharacterDetailFragment.TRANSITION_NAME
                  )
              }*/
        }
    }

    class ItemCharacterDiffCallback : DiffUtil.ItemCallback<Characters>() {

        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean = oldItem == newItem
    }
}

