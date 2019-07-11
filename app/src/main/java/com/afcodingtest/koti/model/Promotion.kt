package com.afcodingtest.koti.model

data class Promotion(
    var title: String,
    var backgroundImage: String,
    var promoMessage: String,
    var topDescription: String,
    var bottomDescription: String,
    var content: ArrayList<Content>
)