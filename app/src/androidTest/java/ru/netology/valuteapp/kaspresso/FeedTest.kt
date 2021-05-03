package ru.netology.valuteapp.kaspresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
import ru.netology.valuteapp.kaspresso.screen.MainScreen

@RunWith(AndroidJUnit4::class)
class FeedTest : KTestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val LIST_ITEM_IN_TEST = 4

    @Test
    fun checkFeedFragmentsMovie() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.feedFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun checkListVisibleFragments() {
        onView(withId(R.id.rv_valute_list)).check(matches(isDisplayed()))
    }

    @Test
    fun checkListConvertFragments() {
        onView(withId(R.id.rv_valute_list))
            .perform(actionOnItemAtPosition<ValuteViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.converterFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun pressBackListConvertFragments() {
        onView(withId(R.id.rv_valute_list))
            .perform(actionOnItemAtPosition<ValuteViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.converterFragment)).check(matches(isDisplayed()))
        pressBack()
    }

    @Test
    @TestCase(name = "Test-1", description = "Check feed displayed with hint")
    fun checkFeedDisplayed() {
        run {
            step("Check content") {
                checkValutes(
                    Valute(
                        id = "R01010",
                        numCode = "036",
                        charCode = "AUD",
                        nominal = 1,
                        name = "Австралийский доллар",
                        valueValute = "58.1546",
                        previous = "57.8546"
                    ),
                    Valute(
                        id = "R01020A",
                        numCode = "944",
                        charCode = "AZN",
                        nominal = 1,
                        name = "Азербайджанский манат",
                        valueValute = "44.0524",
                        previous = "43.78"
                    ),
                    Valute(
                        id = "R01035",
                        numCode = "826",
                        charCode = "GBP",
                        nominal = 1,
                        name = "Фунт стерлингов Соединенного королевства",
                        valueValute = "104.1694",
                        previous = "103.7782"
                    ),
                    Valute(
                        id = "R01060",
                        numCode = "051",
                        charCode = "AMD",
                        nominal = 100,
                        name = "Армянс ких драмов",
                        valueValute = "14.3761",
                        previous = "14.3095"
                    ),
                    Valute(
                        id = "R01090B",
                        numCode = "933",
                        charCode = "BYN",
                        nominal = 1,
                        name = "Белорусский рубль",
                        valueValute = "29.2398",
                        previous = "29.076"
                    )
                )
            }
        }
    }

    class Valute(
        val id: String,
        val numCode: String,
        val charCode: String,
        val nominal: Int,
        val name: String,
        val valueValute: String,
        val previous: String
    )

    private fun checkValutes(vararg valutes: Valute) {
        valutes.forEachIndexed { index, valute ->
            MainScreen {
                feedList {
                    childAt<MainScreen.ValuteCard>(index) {
                        id {
                            isDisplayed()
                            hasText(valute.id)
                        }
                        numCode {
                            isDisplayed()
                            hasText(valute.numCode)
                        }
                        charCode {
                            isDisplayed()
                            hasText(valute.charCode)
                        }
                        nominal {
                            isDisplayed()
                            hasText(valute.nominal)
                        }
                        name {
                            isDisplayed()
                            hasText(valute.name)
                        }
                        valueValute {
                            isDisplayed()
                            hasText(valute.valueValute)
                        }
                        previous {
                            isDisplayed()
                            hasText(valute.previous)
                        }
                    }
                }
            }
        }
    }
}