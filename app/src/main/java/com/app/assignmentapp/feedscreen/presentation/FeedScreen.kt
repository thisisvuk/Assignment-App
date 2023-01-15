@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.app.assignmentapp.feedscreen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.app.assignmentapp.R
import com.app.assignmentapp.TabItem
import com.app.assignmentapp.feedscreen.data.Post
import com.app.assignmentapp.feedscreen.pagination.MainViewModel
import com.app.assignmentapp.ui.dimensions
import com.app.assignmentapp.ui.theme.Blue
import com.app.assignmentapp.ui.theme.Blue40
import com.app.assignmentapp.ui.theme.type
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
lateinit var navCController: NavHostController

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen(navController: NavHostController? = null) {

    if (navController != null) {
        navCController = navController
    }

    val list = listOf(TabItem.Charcha, TabItem.Bazaar, TabItem.Profile)
    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        }) {
        tabs.forEachIndexed { index, tabItem ->

            Tab(
                text = { Text(text = tabItem.title, style = MaterialTheme.type.headlineMedium) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = Blue,
                unselectedContentColor = Gray,
                enabled = true
            )

        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}

@Composable
fun Feeds(navController: NavHostController? = navCController) {
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state
    val scope = rememberCoroutineScope()
    LazyColumn {
        items(state.items.size) { i ->
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                LaunchedEffect(key1 = state) {
                    viewModel.loadNextItems()
                    scope.launch {
                    }
                }
            }
            val post = state.items[i]
            PostCard(
                post = post, navController = navController
            )
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.dimensions.small),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun PostCard(post: Post, navController: NavHostController? = navCController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = MaterialTheme.dimensions.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = MaterialTheme.dimensions.medium)
        ) {
            Image(
                painter = painterResource(id = post.profile),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(MaterialTheme.dimensions.profile)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = MaterialTheme.dimensions.medium),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = post.name,
                        style = MaterialTheme.type.headlineSmall,
                        color = Black,
                        modifier = Modifier.padding(end = MaterialTheme.dimensions.medium)
                    )
                    Box(modifier = Modifier.background(color = Blue40)) {
                        Text(
                            text = post.type,
                            style = MaterialTheme.type.bodySmall,
                            color = Blue,
                            modifier = Modifier.padding(MaterialTheme.dimensions.extraSmall)
                        )
                    }
                }
                Text(
                    text = "${post.time} hours ago",
                    style = MaterialTheme.type.bodyMedium,
                    color = Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = R.drawable.ic_more_horizontal),
                    contentDescription = "",
                    tint = Gray,
                    modifier = Modifier
                        .height(MaterialTheme.dimensions.extraLarge)
                )
            }
        }

        Text(
            text = post.text,
            style = MaterialTheme.type.bodyLarge,
            color = Black,
            modifier = Modifier.padding(
                start = MaterialTheme.dimensions.medium,
                top = MaterialTheme.dimensions.large,
                end = MaterialTheme.dimensions.medium
            )
        )

        ImageSlide(post.image)


        Row {
            val iconSize = MaterialTheme.dimensions.large
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like_filled),
                    contentDescription = "",
                    tint = Blue,
                    modifier = Modifier.height(iconSize)
                )
                Text(
                    text = "${post.likes} Likes",
                    style = MaterialTheme.type.bodyMedium,
                    color = Black,
                    modifier = Modifier.padding(start = MaterialTheme.dimensions.medium)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
                    .clickable {
                        navController?.navigate("comments/${post.id}")
                    }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment_outline),
                    contentDescription = "Localized description",
                    tint = Blue,
                    modifier = Modifier.height(iconSize)
                )
                Text(
                    text = "${post.comments} Comments",
                    style = MaterialTheme.type.bodyMedium,
                    color = Black,
                    modifier = Modifier.padding(start = MaterialTheme.dimensions.medium)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share_outline),
                    contentDescription = "Localized description",
                    tint = Blue,
                    modifier = Modifier.height(iconSize)
                )
                Text(
                    text = "Share",
                    style = MaterialTheme.type.bodyMedium,
                    color = Black,
                    modifier = Modifier.padding(start = MaterialTheme.dimensions.medium)
                )
            }
        }
        Divider(
            color = Gray, thickness = MaterialTheme.dimensions.divider, modifier = Modifier.padding(
                top = MaterialTheme.dimensions.medium
            )
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ImageSlide(image: ArrayList<Int>) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(1f)
            .padding(vertical = MaterialTheme.dimensions.medium)
    ) { padding ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            val pagerState = rememberPagerState()

            HorizontalPager(
                count = image.size,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                image.forEach { id ->
                    Image(
                        painter = painterResource(id = id),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }

            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        horizontal = MaterialTheme.dimensions.medium,
                        vertical = MaterialTheme.dimensions.medium
                    )
            )
        }
    }
}

@Preview(name = "NEXUS_7", device = Devices.PIXEL_XL)
@Composable
fun DefaultPreview() {
    Surface(Modifier.fillMaxSize()) {
        Feeds(null)
    }
}