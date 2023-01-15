@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.app.assignmentapp.commentscreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.app.assignmentapp.R
import com.app.assignmentapp.ui.dimensions
import com.app.assignmentapp.ui.theme.AssignmentAppTheme
import com.app.assignmentapp.ui.theme.Blue
import com.app.assignmentapp.ui.theme.Blue40
import com.app.assignmentapp.ui.theme.type
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

val comments = listOf("Insightful!", "Thanks for sharing!", "Nice Move...")

@Composable
fun CommentsScreen(navController: NavHostController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.dimensions.medium,
                        vertical = MaterialTheme.dimensions.extraLarge
                    )
                    .clickable {
                        navController?.navigate("feed")
                    }
            )
            Text(
                text = "Comments",
                style = MaterialTheme.type.headlineLarge,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.dimensions.large,
                        vertical = MaterialTheme.dimensions.extraLarge
                    )
                    .wrapContentSize()
            )
        }

        LazyColumn {
            item {
                Post()
            }
            items(comments) { comment ->
                Comments(comment)
            }
        }
    }

}

@Composable
fun Post() {

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
                painter = painterResource(id = R.drawable.profile),
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
                    androidx.compose.material.Text(
                        text = "\"Ayush Agarwal",
                        style = MaterialTheme.type.headlineSmall,
                        color = Color.Black,
                        modifier = Modifier.padding(end = MaterialTheme.dimensions.medium)
                    )
                    Box(modifier = Modifier.background(color = Blue40)) {
                        androidx.compose.material.Text(
                            text = "SOIL AWARENESS",
                            style = MaterialTheme.type.bodySmall,
                            color = Blue,
                            modifier = Modifier.padding(MaterialTheme.dimensions.extraSmall)
                        )
                    }
                }
                androidx.compose.material.Text(
                    text = "1 hours ago",
                    style = MaterialTheme.type.bodyMedium,
                    color = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_horizontal),
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier
                        .height(MaterialTheme.dimensions.extraLarge)
                )
            }
        }

        Text(
            text = "Do organic farming and save soil from harmful chemicals.",
            style = MaterialTheme.type.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(
                start = MaterialTheme.dimensions.medium,
                top = MaterialTheme.dimensions.large,
                end = MaterialTheme.dimensions.medium
            )
        )

        ImageSlide(arrayListOf(R.drawable.photo1, R.drawable.photo1))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val iconSize = MaterialTheme.dimensions.large
            Text(
                text = "12 Comments",
                style = MaterialTheme.type.bodyMedium,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = MaterialTheme.dimensions.medium)
                    .weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.dimensions.medium)
                    .weight(1f)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort_icon),
                    contentDescription = "",
                    tint = Blue,
                    modifier = Modifier.height(iconSize)
                )
                Text(
                    text = "Recent",
                    style = MaterialTheme.type.bodyMedium,
                    color = Blue,
                    modifier = Modifier.padding(start = MaterialTheme.dimensions.medium)
                )
            }
        }
        androidx.compose.material.Divider(
            color = Color.Gray,
            thickness = MaterialTheme.dimensions.divider,
            modifier = Modifier.padding(
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
                    ),
            )
        }
    }
}

@Composable
fun Comments(comment: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = MaterialTheme.dimensions.medium,
                horizontal = MaterialTheme.dimensions.medium
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
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
                        text = "Vaibhav Kakde",
                        style = MaterialTheme.type.headlineSmall,
                        color = Color.Black,
                        modifier = Modifier.padding(end = MaterialTheme.dimensions.medium)
                    )

                }
                Text(
                    text = "Public", style = MaterialTheme.type.bodyMedium, color = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier
                        .height(MaterialTheme.dimensions.extraLarge)
                )
            }

        }

        Text(
            text = comment,
            style = MaterialTheme.type.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(top = MaterialTheme.dimensions.large)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = MaterialTheme.dimensions.large)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like_filled),
                contentDescription = "",
                tint = Blue,
                modifier = Modifier.height(MaterialTheme.dimensions.large)
            )
            Text(
                text = "Like",
                style = MaterialTheme.type.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(start = MaterialTheme.dimensions.small)
            )
        }
    }
    Divider(
        color = Color.Gray,
        thickness = MaterialTheme.dimensions.divider,
        modifier = Modifier.padding(
            vertical = MaterialTheme.dimensions.small
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AssignmentAppTheme {
        CommentsScreen()
    }
}