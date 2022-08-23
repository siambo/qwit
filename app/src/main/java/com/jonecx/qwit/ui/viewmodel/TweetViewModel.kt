package com.jonecx.qwit.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.jonecx.qwit.model.Tweet
import com.jonecx.qwit.ui.usecases.HomeTimelineUseCase
import com.jonecx.qwit.util.Result.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import com.jonecx.qwit.util.Result

class TweetViewModel(
    homeTimelineUseCase: HomeTimelineUseCase
): QwitViewModel() {
    val homeTimelineState: StateFlow<Result<List<Tweet>>> =
        homeTimelineUseCase(Unit)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Loading)
}