package com.motiv8.quote.feature_quote.presentation.quotes.components.quote_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.motiv8.quote.R
import com.motiv8.quote.feature_quote.domain.model.Quote
import com.motiv8.quote.feature_quote.presentation.navigation.BottomBarScreen
import com.motiv8.quote.ui.theme.Shapes
import com.motiv8.quote.ui.theme.iconColor
import com.motiv8.quote.ui.theme.purple

@ExperimentalMaterial3Api
@Composable
fun QuoteItem(
    modifier: Modifier = Modifier,
    quote: Quote,
    @DrawableRes drawable: Int = R.drawable.placeholder,
    onClick: () -> Unit
) {
    val offset = Offset(0.0f, 0.0f)

    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = Shapes.medium
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(Color.Black)
                    .alpha(0.6f),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(quote.quoteImgUrl)
                    .placeholder(drawable)
                    .allowHardware(false)
                    //.error(R.drawable.error)
                    .build(),
                contentDescription = stringResource(R.string.description),
            )

            Column(
                modifier = Modifier
                    .padding(
                        top = 70.dp,
                        end = 30.dp,
                        start = 30.dp,
                        bottom = 25.dp
                    ),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = quote.quoteContent.uppercase(),
                    style = MaterialTheme.typography.headlineSmall.copy(
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
                        .height(60.dp)
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

    }

}

@Composable
fun ButtonsGroup(
    modifier: Modifier = Modifier,
    screen: BottomBarScreen,
    onClick: (ImageVector) -> Unit
) {
    val iconsList = arrayListOf(
        if (screen.route == BottomBarScreen.Home.route)
            Icons.Outlined.FavoriteBorder
        else
            Icons.Filled.Favorite,
        Icons.Outlined.Share,
        Icons.Filled.ContentCopy
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ){
        iconsList.forEach{ icon ->
            GlassmorphicIcon(
                icon = icon,
                iconColor = Color(0x20FFFFFF),
                iconTint = Color.White,
                onClick = {  onClick(icon) },
                modifier = Modifier.padding(2.dp)
            )
//            Icon(
//                modifier = Modifier
//                    .clickable {
//                        onClick(icon)
//                    }
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(iconColor)
//                    .padding(10.dp),
//
//                imageVector = icon,
//                tint = purple,
//                contentDescription = null
//            )
        }
    }

}
@Composable
fun GlassmorphicContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x40FFFFFF),
                        Color(0x10FFFFFF)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 0.5.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color.White.copy(alpha = 0.4f),
                        Color.White.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        content()
    }
}
@Composable
fun GlassmorphicIcon(
    icon: ImageVector,
    iconColor: Color,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(10.dp))
    ) {
        // Background layer with blur
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            iconColor.copy(alpha = 0.3f),
                            iconColor.copy(alpha = 0.2f)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                )
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.3f),
                            Color.White.copy(alpha = 0.1f)
                        )
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .blur(radius = 1.dp)  // Blur only applied to background
        )

        // Semi-transparent overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = Color.White.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
        )

        // Icon on top, without blur
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    QuoteItem(
        quote = Quote(
            quoteId = 0,
            quoteContent = "You don't get what you want. You only get what you are",
            quoteAuthor = "Shivam Gupta",
            quoteImgUrl = ""
        ),
        onClick = {}
    )

}
