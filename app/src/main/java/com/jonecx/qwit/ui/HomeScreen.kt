package com.jonecx.qwit.ui

import android.app.Activity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.jonecx.qwit.R
import com.jonecx.qwit.model.Tweet
import com.jonecx.qwit.ui.design.component.QwitTopAppBar
import com.jonecx.qwit.ui.design.theme.QwitTheme
import com.jonecx.qwit.ui.viewmodel.TweetViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    HomeScreen(windowSizeClass, modifier)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {

    QwitGradientBackground {
        Scaffold(
            topBar = {
                QwitTopAppBar(
                    titleRes = R.string.home,
                    navigationIcon = ImageVector.vectorResource(id = R.drawable.ic_home_filled),
                    navigationIconContentDescription = stringResource(id = R.string.home),
                    actionIcon = ImageVector.vectorResource(id = R.drawable.ic_default_avatar),
                    actionIconContentDescription = stringResource(id = R.string.home),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    )
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            val tweetViewModel = getViewModel<TweetViewModel>()
//            val homeTimelineUiState by tweetViewModel.homeTimelineState.collectAsStateWithLifecycle()
//            print(homeTimelineUiState.data?.size ?: "")


            val context = LocalContext.current
            val jsonString: String = context
                .assets
                .open("hometimeline.json")
                .bufferedReader()
                .use { it.readText() }
            val moshi = Moshi.Builder().build()
            val types = Types.newParameterizedType(List::class.java, Tweet::class.java)
            val adapter: JsonAdapter<List<Tweet>> = moshi.adapter(types)
            val tweets = adapter.fromJson(jsonString) ?: emptyList()

            BoxWithConstraints(
                modifier = modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
            ) {
                val localView = LocalView.current
                LaunchedEffect(Unit) {
                    val activity = localView.context as? Activity ?: return@LaunchedEffect
                    localView.doOnPreDraw {
                        activity.reportFullyDrawn()
                    }
                }

                LazyColumn {
                    items(tweets) {tweet ->
                        TweetView(tweet = tweet)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun HomeScreenPreview() {
    BoxWithConstraints {
        QwitTheme {
            HomeScreen(
                windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight))
            )
        }
    }
}
