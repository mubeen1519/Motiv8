package com.motiv8.quote.feature_quote.presentation.quotes.components

import com.motiv8.quote.feature_quote.domain.model.Quote

sealed class QuoteEvent{
    object GetQuotesFromApi : QuoteEvent()
    object GetQuotesFromDb: QuoteEvent()
    class InsertQuote(val quote: Quote): QuoteEvent()
    class DeleteQuote(val quote: Quote): QuoteEvent()
}
