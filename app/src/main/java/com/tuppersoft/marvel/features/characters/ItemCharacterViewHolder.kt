package com.tuppersoft.marvel.features.characters

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.tuppersoft.domain.customtypealias.OnClickItemBinding
import com.tuppersoft.domain.models.character.Characters
import com.tuppersoft.marvel.databinding.CharacterItemBinding
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment

class ItemCharacterViewHolder(private val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(characters: Characters, onClickItem: OnClickItemBinding<Characters, CharacterItemBinding>?) {

        binding.character = characters
        setTransitionIds(characters)
        binding.cardCharacter.setOnClickListener {
            onClickItem?.invoke(characters, binding)
        }
    }

    private fun setTransitionIds(item: Characters?) {
        item?.let {
            ViewCompat.setTransitionName(
                binding.transformationLayout,
                item.id.toString() + CharacterDetailFragment.TRANSITION_LAYOUT
            )
        }
    }
}
