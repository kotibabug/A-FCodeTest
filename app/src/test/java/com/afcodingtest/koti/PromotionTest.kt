package com.afcodingtest.koti

import com.afcodingtest.koti.model.Promotion
import org.junit.Test

import org.junit.Assert.*

class PromotionTest {
    @Test
    fun testPromotion() {
        val promotion = Promotion()
        promotion.title = "test"
        promotion.topDescription = "Hello"
        val list = arrayListOf<Promotion.Content>()
        val content = promotion.Content()
        content.title = "title"
        list.add(content)
        promotion.content = list
        assertEquals("test", promotion.title)
        assertEquals("Hello", promotion.topDescription)
        assertEquals("title", promotion.content?.get(0)?.title)
    }
}
