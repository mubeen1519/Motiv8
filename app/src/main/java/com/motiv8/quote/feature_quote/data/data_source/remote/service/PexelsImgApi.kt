package com.motiv8.quote.feature_quote.data.data_source.remote.service

import com.motiv8.quote.BuildConfig
import com.motiv8.quote.feature_quote.domain.model.PexelsImageList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsImgApi {
    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET("v1/search?per_page=20")
    suspend fun getImages(
        @Query("query") query: String
    ): Response<PexelsImageList>
}

