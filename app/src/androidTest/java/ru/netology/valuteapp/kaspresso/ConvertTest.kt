package ru.netology.valuteapp.kaspresso

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.netology.valuteapp.R
import ru.netology.valuteapp.activity.MainActivity
import ru.netology.valuteapp.adapter.ValuteViewHolder
import ru.netology.valuteapp.kaspresso.annotation.TestCase

@RunWith(AndroidJUnit4::class)
class ConvertTest : KTestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val LIST_ITEM_IN_TEST = 10


    @Test
    @TestCase(name = "Test-2", description = "Test convert value")
    fun convertValueTest() {
        val value = 1000
        onView(withId(R.id.rv_valute_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ValuteViewHolder>(
                    LIST_ITEM_IN_TEST,
                   click()
                )
            )
        onView(withId(R.id.et_input_value)).perform(typeText(value.toString()))
        closeSoftKeyboard()
        onView(withId(R.id.fab_btn_save)).perform(click())

    }
}