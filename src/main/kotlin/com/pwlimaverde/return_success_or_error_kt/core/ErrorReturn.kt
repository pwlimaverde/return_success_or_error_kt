package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.AppError

class ErrorReturn<R>(error: AppError) : ReturnSuccessOrError<R>(error, null) {
    val result = error
    override fun toString(): String {
        return "ErrorReturn - $result"
    }
}