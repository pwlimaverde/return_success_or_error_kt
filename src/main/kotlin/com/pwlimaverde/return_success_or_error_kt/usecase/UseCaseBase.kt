package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

interface UseCaseBase<TypeUseCase, Parameters : ParametersReturnResult> {
    suspend operator fun invoke(parameters: Parameters): ReturnSuccessOrError<TypeUseCase>

    suspend fun invokeCoroutine(parameters: Parameters): ReturnSuccessOrError<TypeUseCase> {
        return withContext(Dispatchers.Default) {
            println("InvokeCoroutine Thread: ${Thread.currentThread().name}")
            invoke(parameters)
        }
    }
}