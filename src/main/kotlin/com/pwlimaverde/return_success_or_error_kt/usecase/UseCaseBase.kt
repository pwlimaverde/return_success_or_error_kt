package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult
import kotlinx.coroutines.suspendCancellableCoroutine

interface UseCaseBase<TypeUseCase, Parameters : ParametersReturnResult> {
    operator fun invoke(parameters: Parameters): ReturnSuccessOrError<TypeUseCase>

    suspend fun invokeThread(parameters: Parameters): ReturnSuccessOrError<TypeUseCase> {
        val result = suspendCancellableCoroutine { continuation ->
            invokeInNewThreadAsync(parameters) { data ->
                continuation.resumeWith(Result.success(data))
            }
        }
        return result
    }

    private fun invokeInNewThreadAsync(
        parameters: Parameters,
        callback: (ReturnSuccessOrError<TypeUseCase>) -> Unit,
    ) {
        Thread {
            val result = invoke(parameters)
            callback(result)
        }.start()
    }
}