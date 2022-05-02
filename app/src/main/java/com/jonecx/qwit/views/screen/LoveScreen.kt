package com.jonecx.qwit.views.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.jonecx.qwit.R.drawable
import com.jonecx.qwit.R.string
import com.jonecx.qwit.views.component.QwitIcon

@Composable
fun LoveScreen() {
    Row {
        QwitIcon(
            iconId = drawable.ic_heart_filled,
            contentDescId = string.love,
            tint = MaterialTheme.colors.secondary,
            padding = 180.dp,
            string.tt_love_screen_center_image
        )
    }
}
