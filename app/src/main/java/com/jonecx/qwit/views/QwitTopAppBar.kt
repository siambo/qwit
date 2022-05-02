package com.jonecx.qwit.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter.Companion
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jonecx.qwit.R

@Composable
fun qwitTopAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 16.dp, top = 4.dp)
    ) {
        Image(
            painterResource(
                id = R.drawable.ic_default_avatar
            ),
            contentDescription = null,
            colorFilter = Companion.tint(colorResource(id = R.color.light_gray)),
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
        )
        Text("Twitter", modifier = Modifier.padding(start = 8.dp))
    }
}
