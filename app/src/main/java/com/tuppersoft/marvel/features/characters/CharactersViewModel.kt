package com.tuppersoft.marvel.features.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuppersoft.domain.baseusecase.GlobalUseCase.None
import com.tuppersoft.domain.models.Characters
import com.tuppersoft.domain.usescase.GetCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersViewModel @ViewModelInject constructor(private val getCharacters: GetCharacters) : ViewModel() {

    private val _characters: MutableLiveData<List<Characters>> = MutableLiveData()
    val characters: LiveData<List<Characters>> get() = _characters

    init {
        getCharactersList()
    }

    fun getCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacters.invoke(None()).catch {
                it
            }.collect {
                val newList = _characters.value?.let { resultList ->
                    resultList + it.results
                } ?: it.results
                _characters.postValue(newList)
            }
        }
    }
}
