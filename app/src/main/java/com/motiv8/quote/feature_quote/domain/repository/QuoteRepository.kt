package com.motiv8.quote.feature_quote.domain.repository

import com.motiv8.quote.feature_quote.domain.model.PexelsImageList
import com.motiv8.quote.feature_quote.domain.model.QuotableQuote
import com.motiv8.quote.feature_quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotesFromDb(): Flow<List<Quote>>

    suspend fun insertQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)

    suspend fun getQuotesText(): List<QuotableQuote>

    suspend fun getImages(): PexelsImageList
}