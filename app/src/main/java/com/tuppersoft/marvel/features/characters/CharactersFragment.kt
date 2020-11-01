package com.tuppersoft.marvel.features.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.skydoves.transformationlayout.addTransformation
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.tuppersoft.domain.models.character.Characters
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.platform.BaseFragment
import com.tuppersoft.marvel.core.platform.GlobalItem
import com.tuppersoft.marvel.core.platform.ShimmerItem
import com.tuppersoft.marvel.databinding.CharacterItemBinding
import com.tuppersoft.marvel.databinding.CharactersFragmentBinding
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment.Companion.CHARACTERS_TAG
import com.tuppersoft.marvel.features.charactersdetails.CharacterDetailFragment.Companion.PARAMS_TRANSFORMATION_LAYOUT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment() {

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: CharactersFragmentBinding
    private val listOfRecyclerView: MutableList<GlobalItem> = mutableListOf()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTransformationStartContainer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObserver()
        viewModel.getCharactersList()
    }

    private fun initObserver() {
        handleCharacterList()
        handleErrors()
    }

    private fun handleErrors() {
        viewModel.failure.observe(viewLifecycleOwner, { value ->
            handleErrorDialog(value.message.toString())
        })
    }

    private fun initRecycler() {
        binding.rvCharacters.adapter = charactersListAdapter
        if (viewModel.isFirstTime()) {
            charactersListAdapter.submitList(
                listOf(
                    ShimmerItem(),
                    ShimmerItem(),
                    ShimmerItem(),
                    ShimmerItem(),
                    ShimmerItem(),
                    ShimmerItem(),
                    ShimmerItem(),
                    ShimmerItem()
                )
            )
        }
        charactersListAdapter.setOnclickItemListener { item, holder ->
            navigateToCharacter(item, holder)
        }


        binding.rvCharacters.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getCharactersList()
                }
            }
        })
    }

    private fun navigateToCharacter(character: Characters, binding: CharacterItemBinding) {

        val fragment = CharacterDetailFragment()
        val bundle = binding.transformationLayout.getBundle(PARAMS_TRANSFORMATION_LAYOUT)
        bundle.putSerializable(CHARACTERS_TAG, character)
        fragment.arguments = bundle

        activity?.apply {
            supportFragmentManager
                .beginTransaction()
                .addTransformation(binding.transformationLayout)
                .replace(R.id.container, fragment, CharacterDetailFragment.TAG)
                .addToBackStack(CharacterDetailFragment.TAG)
                .commit()
        }

        /*
         val extras = FragmentNavigatorExtras(
             binding.imageCharacter to (character.id.toString() + TRANSITION_PHOTO),
             binding.nameCharacter to (character.id.toString() + TRANSITION_NAME)
         )
         findNavController().navigate(
             CharactersFragmentDirections.actionCharactersFragmentIdToCharacterDetailFragmentId(
                 character
             ), extras
         )*/
    }

    private fun handleCharacterList() {
        viewModel.characters.observe(viewLifecycleOwner, { value ->

            val oldSize = listOfRecyclerView.size
            listOfRecyclerView.clear()
            listOfRecyclerView.addAll(value)

            if (oldSize != 0) {
                charactersListAdapter.notifyItemRangeInserted(oldSize, charactersListAdapter.itemCount - oldSize)
            } else {
                charactersListAdapter.submitList(listOfRecyclerView)
                charactersListAdapter.notifyDataSetChanged()
            }
        })
    }
}
