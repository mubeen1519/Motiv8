package com.motiv8.quote.feature_quote.presentation.quotes.components.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.motiv8.quote.feature_quote.presentation.navigation.AnimatedNavigationBar
import com.motiv8.quote.feature_quote.presentation.navigation.BottomBarScreen
import com.motiv8.quote.feature_quote.presentation.navigation.BottomNavGraph
import com.motiv8.quote.feature_quote.presentation.navigation.BottomNavNoAnimation
import com.motiv8.quote.feature_quote.presentation.navigation.ButtonData
import com.motiv8.quote.feature_quote.presentation.navigation.Screen
import com.motiv8.quote.feature_quote.presentation.quotes.viewmodel.QuoteViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val quoteViewModel = viewModel<QuoteViewModel>()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MainTopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        println(innerPadding)

        Log.d("AppStatus", "MainScreen:: Scaffold")

        Box(modifier = Modifier.padding(innerPadding)) {
            Log.d("AppStatus", "MainScreen:: Box")
            BottomNavGraph(
                navController = navController,
                viewModel = quoteViewModel
            )
        }
    }

}


@Composable
private fun MainTopBar() {
    SmallTopAppBar(
        title = {
            Text(text = "  Your Daily Inspiration 🤯")
        },
        colors = TopAppBarDefaults.smallTopAppBarColors()
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController
) {


    val buttons = listOf(
        ButtonData("Home", Icons.Filled.Home),
        ButtonData("Favorite", Icons.Filled.Favorite),
    )
    val screen = listOf(
        Screen.Home,
        Screen.Favorite,
    )

//    AnimatedNavigationBar(
//        modifier = Modifier
//            .fillMaxWidth().height(70.dp),
//        buttons = buttons,
//        barColor = MaterialTheme.colorScheme.onTertiary,
//        circleColor = MaterialTheme.colorScheme.onTertiary,
//        selectedColor = if(isSystemInDarkTheme()) Color(0xffFFE1FF) else Color(0xff0096FF),
//        unselectedColor = Color.Gray, // Give an appropriate height to the bottom bar
//        navController = navController
//    )
    BottomNavNoAnimation(screens = screen, navController = navController, barColor = MaterialTheme.colorScheme.onTertiary)

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {

            navController.navigate(screen.route) {

                //Pop up to a given destination before navigating.
                // Navigate the user to startDestination (Home)
                popUpTo(navController.graph.startDestinationId)

                //This will allow us to store multiple copies of same destination
                launchSingleTop = true
            }

        }
    )
}

