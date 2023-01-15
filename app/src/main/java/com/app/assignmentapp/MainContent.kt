package com.app.assignmentapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.assignmentapp.commentscreen.presentation.CommentsScreen
import com.app.assignmentapp.feedscreen.presentation.FeedScreen

@Composable
fun MainContent(navController: NavHostController) {

    Surface(modifier = Modifier.fillMaxSize()) {
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
