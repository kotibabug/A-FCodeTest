package com.afcodingtest.koti

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.presenter.PromotionsPresenter
import com.afcodingtest.koti.ui.view.PromotionsActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowCrossProfileApps
import org.robolectric.shadows.ShadowIntent
import android.content.Intent
import com.afcodingtest.koti.ui.view.WebActivity
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
@Config(shadows = [ShadowSnackbar::class])
class PromotionsActivityTest {
    private lateinit var activity: PromotionsActivity


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(PromotionsActivity::class.java).create().resume().get()
        assertNotNull(activity)
    }

    @Test
    fun testLoadingIndicator() {
        activity.showLoadingError("error")
        assertEquals("error", ShadowSnackbar.getTextOfLatestSnackbar())
    }

    @Test
    fun testLoadPromotions() {
        val list = ArrayList<Promotion>()
        activity.showPromotions(list)
        val view = activity.findViewById<RecyclerView>(R.id.item_list)
        assertNotNull(view.adapter)
    }

    @Test
    fun testShowDetails() {
        activity.showDetail("http://")
        val startedIntent = shadowOf(activity).nextStartedActivity
        val shadowIntent = shadowOf(startedIntent)
        assertEquals(WebActivity::class.java, shadowIntent.intentClass)
    }
}