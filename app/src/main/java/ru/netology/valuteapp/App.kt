package ru.netology.valuteapp

import android.app.Application
import ru.netology.valuteapp.db.AppDb
import ru.netology.valuteapp.repository.IValuteRepository
import ru.netology.valuteapp.repository.ValuteRepositoryImpl

class App : Application() {

    companion object {
        lateinit var repository: IValuteRepository
    }

    override fun onCreate() {
        super.onCreate()
        repository = ValuteRepositoryImpl(
            AppDb.getInstance(applicationContext)
                .ValuteDao()
        )
    }
}