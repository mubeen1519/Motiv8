package com.motiv8.quote.feature_quote.domain.use_cases

import com.motiv8.quote.feature_quote.data.repository.QuoteRepositoryImpl
import com.motiv8.quote.feature_quote.domain.model.Quote

class RemoveQuoteFromFav(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    suspend operator fun invoke(quote: Quote){
        quoteRepositoryImpl.deleteQuote(quote)
    }
}