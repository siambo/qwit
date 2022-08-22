package com.jonecx.qwit.ui.usecases

import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.model.User
import com.jonecx.qwit.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserIdUseCase(
    private val qwitClient: QwitClient
) : FlowUseCase<Unit, User>(Dispatchers.IO) {
    override fun execute(parameters: Unit): Flow<Result<User>> = flow {
        qwitClient.authService().getAccountCredentials().let {
            emit(Result.Success(it))
        }
    }
}
