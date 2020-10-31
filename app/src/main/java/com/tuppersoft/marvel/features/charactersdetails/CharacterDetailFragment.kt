package com.tuppersoft.marvel.features.charactersdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.tuppersoft.domain.models.Characters
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.extension.loadFromUrl
import com.tuppersoft.marvel.databinding.CharacterDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    companion object {

        const val TRANSITION_PHOTO = "PHOTO"
        const val TRANSITION_NAME = "NAME"
    }

    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var binding: CharacterDetailFragmentBinding

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTransitionIds(args.character)
        binding.character = args.character
    }

    private fun setTransitionIds(item: Characters?) {
        item?.let {
            binding.imageCharacter.transitionName = item.id.toString() + TRANSITION_PHOTO
            binding.nameCharacter.transitionName = item.id.toString() + TRANSITION_NAME
        }
    }
}
