package com.motiv8.quote.feature_quote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuotableQuoteList(
    val quotes: Map<String, QuotableQuote> // A map to handle the numbered keys
)
