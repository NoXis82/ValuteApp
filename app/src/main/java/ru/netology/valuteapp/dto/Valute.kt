package ru.netology.valuteapp.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Valute(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: String,
    @SerializedName("Previous")
    val previous: String
)

