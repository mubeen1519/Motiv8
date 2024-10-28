package com.motiv8.quote.feature_quote.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.motiv8.quote.feature_quote.domain.model.Quote

@Database(
    entities = [Quote::class],
    version = 1,
    exportSchema = false,
)

abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getFavoriteQuoteDao(): FavoriteQuoteDao
}