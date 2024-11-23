package com.motiv8.quote.feature_quote.presentation.quotes.components.quote_ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.motiv8.quote.feature_quote.domain.model.Quote
import com.motiv8.quote.feature_quote.presentation.navigation.BottomBarScreen
import com.motiv8.quote.feature_quote.presentation.quotes.viewmodel.QuoteViewModel

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun QuoteList(
    modifier: Modifier = Modifier,
    quoteList: List<Quote>,
    viewModel: QuoteViewModel,
    screen: BottomBarScreen
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp)
    ) {
        items(quoteList.size) { i ->
            if (quoteList.isNotEmpty()) {
                if (quoteList[i].quoteContent.isNotEmpty() &&
                    quoteList[i].quoteAuthor.isNotEmpty() &&
                    quoteList[i].quoteContent.isNotEmpty()
                ) {
                    QuoteCard(
                        modifier = Modifier.animateItemPlacement()
                            .height(250.dp)
                            .width(300.dp),// Maintain a fixed aspect ratio (e.g., 3:2) // Inner padding for individual cards
                        Quote(
                            quoteId = quoteList[i].quoteId,
                            quoteContent = quoteList[i].quoteContent,
                            quoteAuthor = quoteList[i].quoteAuthor,
                            quoteImgUrl = quoteList[i].quoteImgUrl
                        ),
                        quoteViewModel = viewModel,
                        screen = screen
                    )
                }
            }
        }
    }
}