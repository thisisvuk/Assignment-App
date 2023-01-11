package com.app.assignmentapp

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Charcha : TabItem("charcha", { Feeds() })
    object Bazaar : TabItem("bazaar", { BazaarScreen() })
    object Profile : TabItem("profile", { ProfileScreen() })
}