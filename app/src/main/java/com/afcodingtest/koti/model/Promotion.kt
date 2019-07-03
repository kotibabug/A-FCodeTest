package com.afcodingtest.koti.model

class Promotion {
    var title: String? = null
    var backgroundImage: String? = null
    var promoMessage: String? = null
    var topDescription: String? = null
    var bottomDescription: String? = null
    var content: ArrayList<Content>? = null

    inner class Content {
        var title: String? = null
        var target: String? = null
    }
}