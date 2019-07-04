package com.afcodingtest.koti

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.afcodingtest.koti.ui.view.PromotionsActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class PromotionsActivityTest {

    @get:Rule var activityActivityTestRule = ActivityTestRule<PromotionsActivity>(PromotionsActivity::class.java)

    @Before
    fun setUp() {
        activityActivityTestRule.activity
            .supportFragmentManager.beginTransaction();
    }

    @Test
    fun testPromotionsList() {
        onView(withId(R.id.item_list)).check(matches(isDisplayed()))
    }

}
