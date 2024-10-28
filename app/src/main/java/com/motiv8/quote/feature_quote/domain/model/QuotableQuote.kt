package com.motiv8.quote.feature_quote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuotableQuote(
    val content: String,
    val author: String,
    val length : Int
)