package ru.netology.valuteapp.db

import android.content.Context
import androidx.room.*
import ru.netology.valuteapp.dao.ValuteDao
import ru.netology.valuteapp.dto.ValuteEntity

@Database(entities = [ValuteEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun ValuteDao(): ValuteDao

    companion object {
        @Volatile
        private var instance: AppDb? = null
        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "valute_app.db")
                .allowMainThreadQueries()
                .build()
    }
}