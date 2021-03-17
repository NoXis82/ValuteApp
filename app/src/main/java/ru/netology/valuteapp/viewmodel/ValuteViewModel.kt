package ru.netology.valuteapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.work.*
import kotlinx.coroutines.launch
import ru.netology.valuteapp.App
import ru.netology.valuteapp.R
import ru.netology.valuteapp.dto.Valute
import ru.netology.valuteapp.fragments.FeedFragmentDirections
import ru.netology.valuteapp.model.StateModel
import ru.netology.valuteapp.utils.UploadWorker
import java.io.IOException
import java.math.*
import java.util.concurrent.TimeUnit

class ValuteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = App.repository
    private val _state = MutableLiveData(StateModel())
    val state: LiveData<StateModel>
        get() = _state
    val data: LiveData<List<Valute>>
        get() = repository.valutes

    init {
        load()
        setPeriodUpload()
    }

    fun clickItem(valute: Valute, navController: NavController) {
        val action = FeedFragmentDirections.actionFeedFragmentToConverterFragment(
            name = valute.name,
            nominal = valute.nominal,
            value = valute.value,
            previous = valute.previous
        )
        navController.navigate(action)
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = StateModel(refreshing = true)
            try {
                repository.getAll()
                _state.value = StateModel(refreshing = false)
            } catch (e: IOException) {
                _state.value = StateModel(refreshing = false)
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    R.string.error_connect,
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.value = StateModel(loading = true)
            try {
                repository.getAll()
                _state.value = StateModel(loading = false)
            } catch (e: IOException) {
                _state.value = StateModel(loading = false)
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    R.string.error_connect,
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        }
    }

    private fun setPeriodUpload() {
        val workManager =
            WorkManager.getInstance(getApplication<Application>().applicationContext)
        val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val uploadRequest =
            PeriodicWorkRequest.Builder(UploadWorker::class.java, 16, TimeUnit.MINUTES)
                .setConstraints(constrains)
                .build()
        workManager.enqueue(uploadRequest)
    }

    fun convert(value: String, nominal: Int, convert: String): String = BigDecimal(convert)
        .divide(BigDecimal(value), 2, RoundingMode.CEILING)
        .multiply(BigDecimal(nominal))
        .toString()
}