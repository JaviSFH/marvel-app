package com.tuppersoft.marvel.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuppersoft.domain.models.exceptions.Failure

abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<Failure?> = MutableLiveData()
    val failure: LiveData<Failure?> get() = _failure

    fun handleError(failure: Failure?) {
        _failure.postValue(failure)
       //_failure.postValue(null)
    }
}
