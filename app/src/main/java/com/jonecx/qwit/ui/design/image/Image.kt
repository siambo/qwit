package com.jonecx.qwit.ui.design.image

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp

@Composable
fun Image(@DrawableRes drawableResId: Int, size: Dp, @StringRes contentDescription: Int) {
    val image: Painter = painterResource(id = drawableResId)
    androidx.compose.foundation.Image(
        painter = image,
        contentDescription = stringResource(id = contentDescription),
        modifier = Modifier.size(size)
    )
}
