package com.motiv8.quote.di

import android.app.Application
import androidx.room.Room
import com.motiv8.quote.feature_quote.data.data_source.local.QuoteDatabase
import com.motiv8.quote.feature_quote.data.data_source.remote.service.PexelsImgApi
import com.motiv8.quote.feature_quote.data.data_source.remote.service.QuotableApi
import com.motiv8.quote.feature_quote.data.repository.QuoteRepositoryImpl
import com.motiv8.quote.feature_quote.domain.use_cases.*
import com.motiv8.quote.feature_quote.util.Constants.QUOTE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(app: Application): QuoteDatabase {
        return Room.databaseBuilder(
            app,
            QuoteDatabase::class.java,
            QUOTE_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(
        db: QuoteDatabase,
        pexelsImgApi: PexelsImgApi,
        quotableApi: QuotableApi
    ): QuoteRepositoryImpl {
        return QuoteRepositoryImpl(
            quoteDatabase = db,
            quotableApi = quotableApi,
            pexelsImgApi = pexelsImgApi
        )
    }

    @Provides
    @Singleton
    fun provideQuoteUseCases(quoteRepositoryImpl: QuoteRepositoryImpl): QuoteUseCases{
        return QuoteUseCases(
            AddQuoteToFav(quoteRepositoryImpl),
            RemoveQuoteFromFav(quoteRepositoryImpl),
            GetQuotesFromApi(quoteRepositoryImpl),
            GetQuotesFromDb(quoteRepositoryImpl)
        )
    }


}