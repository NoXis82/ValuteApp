package ru.netology.valuteapp.adapter

import ru.netology.valuteapp.dto.Valute

interface IOnInteractionListener {
    fun onClickItems(valute: Valute)
}