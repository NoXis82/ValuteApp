package ru.netology.valuteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.valuteapp.App
import ru.netology.valuteapp.dto.Valute
import ru.netology.valuteapp.model.StateModel
import java.io.IOException

class ValuteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = App.repository

    private val _state = MutableLiveData(StateModel())
    val state: LiveData<StateModel>
        get() = _state

    val data: LiveData<List<Valute>>
        get() = repository.valutes

    init {
        load()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = StateModel(refreshing = true)
            try {
                repository.getAll()
                _state.value = StateModel(refreshing = false)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            _state.value = StateModel(loading = true)
            try {
                repository.getAll()
                _state.value = StateModel(loading = false)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


}