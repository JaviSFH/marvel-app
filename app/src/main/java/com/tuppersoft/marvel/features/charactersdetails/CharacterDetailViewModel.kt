package com.tuppersoft.marvel.features.charactersdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuppersoft.domain.models.exceptions.Failure
import com.tuppersoft.domain.usescase.GetComicsFromCharactersId
import com.tuppersoft.domain.usescase.GetComicsFromCharactersId.Params
import com.tuppersoft.marvel.core.platform.BaseViewModel
import com.tuppersoft.marvel.features.charactersdetails.comiccover.ComicItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CharacterDetailViewModel @ViewModelInject constructor(private val getComicsFromCharactersId: GetComicsFromCharactersId) :
    BaseViewModel() {

    private val _comic: MutableLiveData<List<ComicItem>> = MutableLiveData()
    val comic: LiveData<List<ComicItem>> get() = _comic

    fun getComicsFromCharacterId(characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getComicsFromCharactersId.invoke(Params(characterId)).catch {
                if (it is Failure) {
                    handleError(it)
                }
            }.map {
                it.results?.map { comic -> ComicItem(comic) } ?: listOf()
            }.collect {
                val newList = _comic.value?.let { resultList ->
                    resultList + it
                } ?: it
                _comic.postValue(newList)
            }
        }
    }
}
