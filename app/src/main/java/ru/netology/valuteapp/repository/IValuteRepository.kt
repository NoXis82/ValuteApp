package ru.netology.valuteapp.repository

import androidx.lifecycle.LiveData
import ru.netology.valuteapp.dto.Valute

interface IValuteRepository {
    val valutes: LiveData<List<Valute>>
    suspend fun getAll(): List<Valute>
}