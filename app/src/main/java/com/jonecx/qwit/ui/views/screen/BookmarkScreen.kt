package com.jonecx.qwit.ui.views.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jonecx.qwit.R.drawable
import com.jonecx.qwit.R.string
import com.jonecx.qwit.ui.theme.QwitTheme
import com.jonecx.qwit.ui.views.component.QwitIcon

@Composable
fun BookmarkScreen() {
    Row {
        QwitIcon(
            iconId = drawable.ic_bookmark_filled,
            contentDescId = string.bookmark,
            tint = MaterialTheme.colors.secondary,
            padding = 180.dp,
            string.tt_bookmark_screen_center_image
        )
    }
}

@Preview
@Composable
fun BookmarkScreenPreview() {
    QwitTheme {
        BookmarkScreen()
    }
}
