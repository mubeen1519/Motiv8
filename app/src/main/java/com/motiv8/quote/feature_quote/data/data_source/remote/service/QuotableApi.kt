package com.motiv8.quote.feature_quote.data.data_source.remote.service

import com.motiv8.quote.feature_quote.domain.model.QuotableQuote
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotableApi {
    @GET("quotes/random")
    suspend fun getQuotes(
        @Query("count") page: Int
    ):  List<QuotableQuote>
}
