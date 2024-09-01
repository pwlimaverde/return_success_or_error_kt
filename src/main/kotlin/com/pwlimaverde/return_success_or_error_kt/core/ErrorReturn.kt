package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.AppError

 data class ErrorReturn<R>(private val error: AppError) : ReturnSuccessOrError<R> {
     val result: AppError = error
    override fun toString(): String {
        return "ErrorReturn - $result"
    }
}