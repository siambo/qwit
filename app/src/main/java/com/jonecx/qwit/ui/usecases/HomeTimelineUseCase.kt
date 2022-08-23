package com.jonecx.qwit.ui.usecases

import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.model.Tweet
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Success
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeTimelineUseCase(
    private val qwitClient: QwitClient
): FlowUseCase<Unit, List<Tweet>>(Dispatchers.IO) {
    override fun execute(parameters: Unit): Flow<Result<List<Tweet>>> = flow {
       emit(Success(qwitClient.authService().getHomeTimeline()))
    }
}