package ru.netology.valuteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.netology.valuteapp.dto.ValuteEntity

@Dao
interface ValuteDao {

    @Query("SELECT * FROM ValuteEntity")
    fun getValutes(): LiveData<List<ValuteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(valute: ValuteEntity)

    @Query("UPDATE ValuteEntity SET value = :value, previous = :previous WHERE id = :id")
    suspend fun update(id: String, value: String, previous: String)

    @Query("SELECT EXISTS(SELECT * FROM ValuteEntity WHERE id = :id)")
    fun isRowIsExist(id: String): Boolean

    @Transaction
    suspend fun insertList(valutes: List<ValuteEntity>) {
        valutes.forEach {
            if (isRowIsExist(it.id)) {
                update(it.id, it.value, it.previous)
            } else {
                insert(it)
            }
        }
    }

}