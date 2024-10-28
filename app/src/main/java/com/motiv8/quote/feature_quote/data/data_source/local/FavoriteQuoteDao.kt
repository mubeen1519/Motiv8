package com.motiv8.quote.feature_quote.data.data_source.local

import androidx.room.*
import com.motiv8.quote.feature_quote.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuoteDao {

    @Query("SELECT * FROM quote_table")
    fun getQuotes(): Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)
}