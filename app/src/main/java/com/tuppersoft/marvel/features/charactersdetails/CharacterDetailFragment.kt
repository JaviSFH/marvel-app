package com.tuppersoft.marvel.features.charactersdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer
import com.tuppersoft.domain.models.character.Characters
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.platform.BaseFragment
import com.tuppersoft.marvel.core.platform.ShimmerItem
import com.tuppersoft.marvel.databinding.CharacterDetailFragmentBinding
import com.tuppersoft.marvel.features.charactersdetails.comiccover.ComicListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    companion object {

        val TAG: String = CharacterDetailFragment::class.java.simpleName
        const val TRANSITION_LAYOUT = "LAYOUT"
        const val PARAMS_TRANSFORMATION_LAYOUT = "PARAMS_TRANSFORMATION_LAYOUT"
        const val CHARACTERS_TAG = "CHARACTERS_TAG"
    }

    private val comicListAdapter: ComicListAdapter by lazy {
        ComicListAdapter()
    }

    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var binding: CharacterDetailFragmentBinding

    //private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = arguments?.getParcelable<TransformationLayout.Params>(PARAMS_TRANSFORMATION_LAYOUT)
        onTransformationEndContainer(params)
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

        val character = arguments?.getSerializable(CHARACTERS_TAG) as Characters

        initRecycler()
        initObserver()

        binding.character = character
        setTransitionIds(character)
        viewModel.getComicsFromCharacterId(character.id.toString())
    }

    private fun initObserver() {
        handleComicList()
        handleErrors()
    }

    private fun handleComicList() {
        viewModel.comic.observe(viewLifecycleOwner, { value ->
            comicListAdapter.submitList(value)
        })
    }

    private fun handleErrors() {
        viewModel.failure.observe(viewLifecycleOwner, { value ->
            handleErrorDialog(value.message.toString())
        })
    }

    private fun initRecycler() {
        binding.rvComic.adapter = comicListAdapter
        comicListAdapter.submitList(listOf(ShimmerItem(), ShimmerItem(), ShimmerItem()))
        comicListAdapter.setOnclickItemListener { }
    }

    private fun setTransitionIds(item: Characters?) {
        item?.let {
            binding.root.transitionName = item.id.toString() + TRANSITION_LAYOUT
        }
    }
}
