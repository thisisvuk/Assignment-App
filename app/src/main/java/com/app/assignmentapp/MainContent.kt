package com.app.assignmentapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainContent() {

    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "feed") {
            composable("feed") {
                FeedScreen(navController)
            }
            composable(
                "comments/{postId}",
                listOf(navArgument("postId") { type = NavType.IntType })
            ) {
                CommentsScreen(navController)
            }
        }

    }

}

@Preview
@Composable
fun MainPreview() {
    MainContent()
}