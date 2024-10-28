package com.motiv8.quote.feature_quote.domain.use_cases

import com.motiv8.quote.feature_quote.data.repository.QuoteRepositoryImpl
import com.motiv8.quote.feature_quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

class GetQuotesFromDb(
    private val quoteRepositoryImpl: QuoteRepositoryImpl
) {
    operator fun invoke(): Flow<List<Quote>> {
        return quoteRepositoryImpl.getQuotesFromDb()
    }
}