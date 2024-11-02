package com.motiv8.quote.feature_quote.data.repository

import com.motiv8.quote.feature_quote.data.data_source.local.QuoteDatabase
import com.motiv8.quote.feature_quote.data.data_source.remote.service.PexelsImgApi
import com.motiv8.quote.feature_quote.data.data_source.remote.service.QuotableApi
import com.motiv8.quote.feature_quote.domain.model.PexelsImageList
import com.motiv8.quote.feature_quote.domain.model.QuotableQuote
import com.motiv8.quote.feature_quote.domain.model.Quote
import com.motiv8.quote.feature_quote.domain.repository.QuoteRepository
import com.motiv8.quote.feature_quote.util.Constants.imgCategoryList
import kotlinx.coroutines.flow.Flow
import java.util.Random
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    quoteDatabase: QuoteDatabase,
    private val quotableApi: QuotableApi,
    private val pexelsImgApi: PexelsImgApi
) : QuoteRepository {

    private val favoriteQuoteDao = quoteDatabase.getFavoriteQuoteDao()

    override suspend fun getImages(): PexelsImageList {
        return pexelsImgApi.getImages(
            query = imgCategoryList[Random().nextInt(16)]
        ).body()!!
    }

    override suspend fun getQuotesText(): List<QuotableQuote> {
        val response = quotableApi.getQuotes(page = 50)
        return response
    }

    override fun getQuotesFromDb(): Flow<List<Quote>> {
        return favoriteQuoteDao.getQuotes()
    }

    override suspend fun insertQuote(quote: Quote) {
        favoriteQuoteDao.insertQuote(quote)
    }

    override suspend fun deleteQuote(quote: Quote) {
        favoriteQuoteDao.deleteQuote(quote)
    }

}


