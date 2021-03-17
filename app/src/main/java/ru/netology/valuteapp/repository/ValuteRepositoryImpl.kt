package ru.netology.valuteapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.valuteapp.api.ValutesApi
import ru.netology.valuteapp.dao.ValuteDao
import ru.netology.valuteapp.dto.Valute
import ru.netology.valuteapp.dto.ValuteEntity
import ru.netology.valuteapp.dto.toDto

class ValuteRepositoryImpl(private val dao: ValuteDao) : IValuteRepository {

    override val valutes: LiveData<List<Valute>>
        get() = dao.getValutes().map(List<ValuteEntity>::toDto)

    override suspend fun getAll(): List<Valute> {
        val networkValute = ValutesApi.retrofitService.getAll()
        val listValute = networkValute.Valute.values.toList()
         dao.insertList(listValute.map(ValuteEntity.Companion::fromDto))
        return listValute
    }
}