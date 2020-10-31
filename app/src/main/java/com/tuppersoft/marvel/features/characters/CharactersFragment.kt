package com.tuppersoft.marvel.features.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.tuppersoft.domain.models.Characters
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.databinding.CharacterItemBinding
import com.tuppersoft.marvel.databinding.CharactersFragmentBinding
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment.Companion.TRANSITION_NAME
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment.Companion.TRANSITION_PHOTO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: CharactersFragmentBinding
    private val charactersListAdapter: CharactersListAdapter by lazy {
        CharactersListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.characters_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initRecycler()
    }

    private fun initObserver() {
        handleCharacterList()
    }

    private fun initRecycler() {
        binding.rvCharacters.adapter = charactersListAdapter
        charactersListAdapter.setOnclickItemListener { item, holder ->
            navigateToCharacter(item, holder)

        }
    }

    private fun navigateToCharacter(character: Characters, binding: CharacterItemBinding) {

        val extras = FragmentNavigatorExtras(
            binding.imageCharacter to (character.id.toString() + TRANSITION_PHOTO),
            binding.nameCharacter to (character.id.toString() + TRANSITION_NAME)
        )
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentIdToCharacterDetailFragmentId(
                character
            ), extras
        )

    }

    private fun handleCharacterList() {
        viewModel.characters.observe(viewLifecycleOwner, { value ->
            charactersListAdapter.submitList(value)
        })
    }
}
