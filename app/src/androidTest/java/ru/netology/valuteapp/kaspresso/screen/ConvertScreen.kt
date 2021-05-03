package ru.netology.valuteapp.kaspresso.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import ru.netology.valuteapp.R

object ConvertScreen: Screen<ConvertScreen>() {
    val valueInput = KEditText { withId(R.id.et_input_value) }
    val loginButton = KButton { withId(R.id.fab_btn_save) }
}