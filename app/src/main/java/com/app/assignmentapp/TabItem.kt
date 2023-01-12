package com.app.assignmentapp

import androidx.compose.runtime.Composable
import com.app.assignmentapp.screens.BazaarScreen
import com.app.assignmentapp.screens.Feeds
import com.app.assignmentapp.screens.ProfileScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Charcha : TabItem("charcha", { Feeds() })
    object Bazaar : TabItem("bazaar", { BazaarScreen() })
    object Profile : TabItem("profile", { ProfileScreen() })
}