@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.app.assignmentapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.app.assignmentapp.R
import com.app.assignmentapp.TabItem
import com.app.assignmentapp.dataclass.Post
import com.app.assignmentapp.pagination.MainViewModel
import com.app.assignmentapp.ui.theme.Blue
import com.app.assignmentapp.ui.theme.Blue40
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
                text = { Text(tabItem.title) },
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
    LazyColumn {
        items(state.items.size) { i ->
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextItems()
            }

            val post = state.items[i]
            PostCard(
                post, navController
            )
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
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
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp)
        ) {
            Image(
                painter = painterResource(id = post.profile),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = post.name,
                        fontSize = 14.sp,
                        color = Black,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Box(modifier = Modifier.background(color = Blue40)) {
                        Text(
                            text = post.type,
                            fontSize = 10.sp,
                            color = Blue,
                            modifier = Modifier.padding(3.dp)
                        )
                    }
                }
                Text(
                    text = "${post.time} hours ago", fontSize = 12.sp, color = Gray
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
                        .height(20.dp)
                )
            }
        }

        Text(
            text = post.text,
            fontSize = 14.sp,
            color = Black,
            modifier = Modifier.padding(12.dp, 15.dp, 12.dp)
        )

        ImageSlide(post.image)

        Row {
            val iconSize = 18.dp
            val iconLableSize: TextUnit = 12.sp
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
                    fontSize = iconLableSize,
                    color = Black,
                    modifier = Modifier.padding(start = 10.dp)
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
                    fontSize = iconLableSize,
                    color = Black,
                    modifier = Modifier.padding(start = 10.dp)
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
                    fontSize = iconLableSize,
                    color = Black,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
        Divider(
            color = Gray, thickness = 0.5.dp, modifier = Modifier.padding(
                top = 12.dp
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
            .padding(vertical = 10.dp)
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
                    .padding(horizontal = 10.dp, vertical = 10.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(Modifier.fillMaxSize()) {
        Feeds(null)
    }
}