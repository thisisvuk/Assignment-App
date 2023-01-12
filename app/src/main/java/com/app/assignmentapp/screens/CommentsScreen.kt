package com.app.assignmentapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.assignmentapp.R
import com.app.assignmentapp.ui.theme.AssignmentAppTheme
import com.app.assignmentapp.ui.theme.Blue
import com.app.assignmentapp.ui.theme.Blue40

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
                    .padding(horizontal = 12.dp, vertical = 20.dp)
                    .clickable {
                        navController?.navigate("feed")
                    }
            )
            Text(
                text = "Comments",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 20.dp)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.padding(start = 10.dp), horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "Ayush Agarwal",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Box(modifier = Modifier.background(color = Blue40)) {
                    Text(
                        text = "SOIL AWARENESS",
                        fontSize = 10.sp,
                        color = Blue,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }
            Text(
                text = "1 hours ago", fontSize = 12.sp, color = Color.Gray
            )
        }

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 12.dp)
    ) {

        Text(
            text = "Do organic farming and save soil from harmful chemicals.",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(12.dp, 15.dp, 12.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .aspectRatio(1f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val iconSize = 18.dp
            val textSize: TextUnit = 13.sp
            Text(
                text = "12 Comments",
                fontSize = textSize,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
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
                    fontSize = textSize,
                    color = Blue,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Composable
fun Comments(comment: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 12.dp, horizontal = 12.dp)
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
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp), horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = "Vaibhav Kakde",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                }
                Text(
                    text = "Public", fontSize = 12.sp, color = Color.Gray
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
                        .height(18.dp)
                )
            }

        }

        Text(
            text = comment,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 15.dp)
        )

        val iconSize = 18.dp
        val iconLableSize: TextUnit = 12.sp
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like_filled),
                contentDescription = "",
                tint = Blue,
                modifier = Modifier.height(iconSize)
            )
            Text(
                text = "Like",
                fontSize = iconLableSize,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
    Divider(
        color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(
            vertical = 5.dp
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