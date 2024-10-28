package com.motiv8.quote.feature_quote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.motiv8.quote.feature_quote.util.Constants.QUOTE_TABLE

@Entity(tableName = QUOTE_TABLE)
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val quoteId: Int = 0,
    val quoteContent: String,
    val quoteAuthor: String,
    val quoteImgUrl: String
)

class InvalidQuoteException(message: String): Exception(message)