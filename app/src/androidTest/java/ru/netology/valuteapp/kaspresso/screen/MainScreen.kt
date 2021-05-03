package ru.netology.valuteapp.kaspresso.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import ru.netology.valuteapp.R
import org.hamcrest.Matcher

object MainScreen : Screen<MainScreen>() {
    val feedList = KRecyclerView(
        builder = { withId(R.id.rv_valute_list) },
        itemTypeBuilder = { itemType(::ValuteCard) }
    )

    class ValuteCard(parent: Matcher<View>) : KRecyclerItem<ValuteCard>(parent) {
        val id = KTextView(parent) { withId(R.id.tv_valute_id) }
        val numCode = KTextView(parent) { withId(R.id.tv_valute_numcode) }
        val charCode = KTextView(parent) { withId(R.id.tv_valute_charcode) }
        val nominal = KTextView(parent) { withId(R.id.tv_valute_nominal) }
        val name = KTextView(parent) { withId(R.id.tv_valute_name) }
        val valueValute = KTextView(parent) { withId(R.id.tv_valute_value) }
        val previous = KTextView(parent) { withId(R.id.tv_valute_previous) }
    }
}