package com.jonecx.qwit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jonecx.qwit.R
import com.jonecx.qwit.R.drawable
import com.jonecx.qwit.R.string
import com.jonecx.qwit.ui.design.image.Image

@Composable
fun StartingScreen() {
    BoxWithConstraints {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(colorResource(id = R.color.black))
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Image(
                drawableResId = drawable.ic_qwit_logo,
                size = 240.dp,
                contentDescription = string.qwit_logo
            )
        }
    }
}
