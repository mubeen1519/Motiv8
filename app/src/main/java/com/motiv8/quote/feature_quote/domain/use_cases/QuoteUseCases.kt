package com.motiv8.quote.feature_quote.domain.use_cases

data class QuoteUseCases(
    val addQuoteToFav: AddQuoteToFav,
    val removeQuoteFromFav: RemoveQuoteFromFav,
    val getQuotesFromApi: GetQuotesFromApi,
    val getQuotesFromDb: GetQuotesFromDb
)