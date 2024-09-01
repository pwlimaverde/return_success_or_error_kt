package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.AppError


data class SuccessReturn<R>(private val success: R) : ReturnSuccessOrError<R> {
    val result: R = success

    override fun toString(): String {
        return "SuccessReturn - $result"
    }
}