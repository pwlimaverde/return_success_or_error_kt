package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

interface UseCaseBase<TypeUseCase, Parameters : ParametersReturnResult> {
    operator fun invoke(parameters: Parameters): ReturnSuccessOrError<TypeUseCase>

    suspend fun invokeCoroutine(parameters: Parameters): ReturnSuccessOrError<TypeUseCase> {
        return withContext(Dispatchers.Default) {
            println("teste invokeThread2 ${Thread.currentThread().name}")
            invoke(parameters)
        }
    }

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
            println("teste invokeInNewThreadAsync ${Thread.currentThread().name}")
            val result = invoke(parameters)
            callback(result)
        }.start()
    }
}