package com.jonecx.qwit.views

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.jonecx.qwit.R
import com.jonecx.qwit.R.drawable

@Composable
fun qwitFab() {
    FloatingActionButton(
        onClick = {},
        content = {
            Icon(
                painter = painterResource(id = drawable.ic_plus),
                contentDescription = null,
            )
        },
        modifier = Modifier.testTag(R.string.tt_floating_button.toString())
    )
}
