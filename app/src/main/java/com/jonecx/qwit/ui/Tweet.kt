package com.jonecx.qwit.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jonecx.qwit.R
import com.jonecx.qwit.model.Tweet
import com.jonecx.qwit.ui.design.theme.QwitTypography
import com.jonecx.qwit.util.toDate


@Composable
fun TweetView(tweet: Tweet) {
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
                text = "Hermela Brook and Marta Solomon follow",
                modifier = Modifier.padding(start = 9.dp),
                style = QwitTypography.titleSmall,
                color = Color.Gray,
            )
        }
        Row(modifier = Modifier.padding(top = 2.dp)) {
            CircleAvatar(tweet)
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    AuthorNameView(tweet = tweet, modifier = Modifier.weight(0.7f, fill = false))
                    Text(modifier = Modifier.weight(0.3f), text = " • ${tweet.createdAt.toDate()}", style = QwitTypography.bodyMedium, color = Color.Gray, maxLines = 1)
                }
                Text(
                    text =  tweet.text,
                    style = QwitTypography.bodyLarge
                )
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TweetAction(drawableResId = R.drawable.ic_replies, actionCount = if (tweet.replyCount > 0) tweet.retweetCount else "")
                    TweetAction(size = 20.dp, drawableResId = R.drawable.ic_retweet, actionCount = if ("0" == tweet.retweetCount) "" else tweet.retweetCount)
                    TweetAction(size = 19.dp, drawableResId = R.drawable.ic_heart_outline, actionCount = if (tweet.favoriteCount > 0) tweet.favoriteCount.toString() else "")
                    TweetAction(drawableResId = R.drawable.ic_share, actionCount = "")
                }
            }
        }
        Row(modifier = Modifier.padding(start = 9.dp, top = 3.dp), verticalAlignment = Alignment.CenterVertically) {
            CircleAvatar(tweet, 37.dp)
            Text("Show this thread", modifier = Modifier.padding(start = 17.dp))
        }
    }
}

@Composable
private fun AuthorNameView(modifier: Modifier, tweet: Tweet) {
    Text(
        buildAnnotatedString {
            val authorName = "${tweet.user.name} @${tweet.user.screenName}"
            append(authorName)
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W700,
                    fontSize = 16.sp,
                    letterSpacing = 0.1.sp
                ),
                start = 0,
                end = authorName.indexOf("@") - 1
            )
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                ),
                start = authorName.indexOf("@"),
                end = authorName.length - 1
            )
        },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun TweetAction(size: Dp = 17.dp, @DrawableRes drawableResId: Int, actionCount: String, contentDescription: String = "liked") {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(drawableResId),
            contentDescription = contentDescription )
        Text(text= actionCount, style = QwitTypography.labelLarge, modifier = Modifier.padding(start = 4.dp))
    }
}

@Composable
fun CircleAvatar(tweet: Tweet, size: Dp = 55.dp) {
    AsyncImage(modifier = Modifier
        .size(size)
        .clip(CircleShape), model = tweet.user.profileImageUrlHttps, contentDescription = tweet.user.name)
}