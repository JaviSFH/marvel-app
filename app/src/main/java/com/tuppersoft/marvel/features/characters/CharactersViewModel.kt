package com.tuppersoft.marvel.features.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuppersoft.domain.models.exceptions.Failure
import com.tuppersoft.domain.usescase.GetCharacters
import com.tuppersoft.domain.usescase.GetCharacters.Params
import com.tuppersoft.marvel.core.platform.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CharactersViewModel @ViewModelInject constructor(private val getCharacters: GetCharacters) : BaseViewModel() {

    private val _characters: MutableLiveData<List<CharacterItem>> = MutableLiveData()
    val characters: LiveData<List<CharacterItem>> get() = _characters
    var totalCharacter = 0
    private var firstTime: Boolean = true

    fun getCharactersList(limit: Int = 10) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacters.invoke(Params(limit, totalCharacter)).catch {
                if (it is Failure) {
                    handleError(it)
                }
            }.map {
                totalCharacter += it.count ?: 0
                it.results.map { character ->
                    CharacterItem(character)
                }
            }.collect {
                val newList = _characters.value?.let { resultList ->
                    resultList + it
                } ?: it
                _characters.postValue(newList)
            }

        }
    }

    fun isFirstTime(): Boolean {
        val flag = firstTime
        firstTime = false
        return flag
    }
}
