package ru.netology.valuteapp.dto

import com.google.gson.annotations.SerializedName

data class FeedValute (
    @SerializedName("PreviousDate")
    val date: String,
    val Valute: Map<String, Valute>
)
