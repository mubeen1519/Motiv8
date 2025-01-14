package com.motiv8.quote.feature_quote.presentation.quotes.components.quote_ui

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.motiv8.quote.feature_quote.domain.model.Quote
import com.motiv8.quote.feature_quote.domain.use_cases.copyTextToClipboard
import com.motiv8.quote.feature_quote.domain.use_cases.shareImage
import com.motiv8.quote.feature_quote.presentation.navigation.BottomBarScreen
import com.motiv8.quote.feature_quote.presentation.quotes.components.QuoteEvent
import com.motiv8.quote.feature_quote.presentation.quotes.viewmodel.QuoteViewModel
import com.motiv8.quote.feature_quote.presentation.util.captureBitmap

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    quote: Quote,
    quoteViewModel: QuoteViewModel,
    screen: BottomBarScreen
) {
    val context = LocalContext.current
    val offset = Offset(1f, 1f)
    var visible by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        val snapShot = captureBitmap {
            QuoteItem(
                modifier = Modifier.padding(8.dp),
                quote = Quote(
                    quoteId = quote.quoteId,
                    quoteContent = quote.quoteContent,
                    quoteAuthor = quote.quoteAuthor,
                    quoteImgUrl = quote.quoteImgUrl,
                )
            ) {
                visible = true
            }
        }
        // Popup Dialog
        if (visible) {
            Dialog(onDismissRequest = { visible = false }) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .animateContentSize(), // Animate size changes
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp) // Fixed height for the image
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surface)
                                .clipToBounds(), // Ensure content is clipped to bounds
                            contentAlignment = Alignment.Center
                        ) {
                            if (quote.quoteImgUrl.isNotEmpty()) {
                                AsyncImage(
                                    model = quote.quoteImgUrl,
                                    contentDescription = "Quote Background",
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.Center,
                            ) {

                                Text(
                                    text = quote.quoteContent.uppercase(),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.SansSerif,
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = offset,
                                            blurRadius = 10f
                                        )
                                    ),
                                    color = Color.White
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(10.dp)
                                        .fillMaxWidth()
                                )

                                Text(
                                    text = quote.quoteAuthor,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontFamily = FontFamily.SansSerif,
                                        shadow = Shadow(
                                            color = Color.Black,
                                            offset = offset,
                                            blurRadius = 25f
                                        )
                                    ),
                                    color = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Button Group
                        ButtonsGroup(
                            onClick = {
                                when (it.name) {
                                    "Outlined.FavoriteBorder" -> {
                                        quoteViewModel.onEvent(QuoteEvent.InsertQuote(quote = quote))
                                        displayToast(text = "Added to favorite", context = context)
                                    }

                                    "Filled.Favorite" -> {
                                        quoteViewModel.onEvent(QuoteEvent.DeleteQuote(quote = quote))
                                        displayToast(
                                            text = "Removed from favourites",
                                            context = context
                                        )
                                    }

                                    "Outlined.Share" -> {
                                        shareImage(bitmap = snapShot.invoke(), context = context)
                                        displayToast(
                                            text = "Sharing quote.......",
                                            context = context
                                        )
                                    }

                                    "Filled.ContentCopy" -> {
                                        copyTextToClipboard(
                                            context,
                                            "${quote.quoteContent}\nAuthor: ${quote.quoteAuthor}"
                                        )
                                        displayToast(
                                            text = "Text copied to clipboard!",
                                            context = context
                                        )
                                    }
                                }
                            },
                            screen = screen
                        )
                    }
                }
            }
        }
    }
}

fun displayToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}