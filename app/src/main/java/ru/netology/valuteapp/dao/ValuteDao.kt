package ru.netology.valuteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.netology.valuteapp.dto.ValuteEntity

@Dao
interface ValuteDao {

    @Query("SELECT * FROM ValuteEntity")
    fun getValutes(): LiveData<List<ValuteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(valutes: List<ValuteEntity>)

}