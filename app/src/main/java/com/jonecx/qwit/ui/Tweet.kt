package com.jonecx.qwit.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jonecx.qwit.R


@Composable
fun Tweet() {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(start = 42.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(R.drawable.ic_heart_filled),
                contentDescription = "liked" )
            Text(
                text = "Michale Dole and Ryan Qi liked",
                modifier = Modifier.padding(start = 9.dp),
                fontSize = 14.sp,
                color = Color.Gray,
            )
        }
        Row(modifier = Modifier.padding(top = 2.dp)) {
            CircleAvatar()
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Row() {
                    Text("Full name")
                    Text("@full_name")
                    Text("12Hr")
                }
                Text("UXR/UX: You can only bring one item to a remote island to assist your research of native use of tools and usability. What do you bring? #TellMeAboutYou")
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TweetAction(drawableResId = R.drawable.ic_replies)
                    TweetAction(size = 20.dp, drawableResId = R.drawable.ic_retweet)
                    TweetAction(size = 19.dp, drawableResId = R.drawable.ic_heart_outline)
                    TweetAction(drawableResId = R.drawable.ic_share)
                }
            }
        }
        Row(modifier = Modifier.padding(start = 9.dp, top = 3.dp), verticalAlignment = Alignment.CenterVertically) {
            CircleAvatar(37.dp)
            Text("Show this thread", modifier = Modifier.padding(start = 17.dp))
        }
    }
}

@Composable
fun TweetAction(size: Dp = 17.dp, @DrawableRes drawableResId: Int, contentDescription: String = "liked") {
    Icon(
        modifier = Modifier.size(size),
        painter = painterResource(drawableResId),
        contentDescription = contentDescription )
}

@Composable
fun CircleAvatar(size: Dp = 55.dp) {
    AsyncImage(modifier = Modifier
        .size(size)
        .clip(CircleShape), model = "https://pbs.twimg.com/profile_images/1272581069297135618/TIgPXQ81_400x400.jpg", contentDescription = "I like")
}