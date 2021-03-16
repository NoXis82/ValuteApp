package ru.netology.valuteapp.dto

import androidx.room.*

@Entity
data class ValuteEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long,
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: String,
    val previous: String
) {
    fun toDto() =
        Valute(
            id,
            numCode,
            charCode,
            nominal,
            name,
            value,
            previous,
        )

    companion object {
        fun fromDto(dto: Valute) = ValuteEntity(
            0,
            dto.id,
            dto.numCode,
            dto.charCode,
            dto.nominal,
            dto.name,
            dto.value,
            dto.previous
        )
    }

}

fun List<ValuteEntity>.toDto(): List<Valute> = map(ValuteEntity::toDto)