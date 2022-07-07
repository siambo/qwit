package com.jonecx.qwit.ui.views.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun QwitIcon(@DrawableRes iconId: Int, @StringRes contentDescId: Int, tint: Color, padding: Dp = 1.dp, @StringRes testTagId: Int = 0) {
    Image(
        painter = painterResource(iconId),
        contentDescription = stringResource(id = contentDescId),
        colorFilter = ColorFilter.tint(tint),
        modifier = Modifier
            .testTag(testTagId.toString())
            .padding(padding)
    )
}
