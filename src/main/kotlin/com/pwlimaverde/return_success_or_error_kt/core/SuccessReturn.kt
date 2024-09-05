package com.pwlimaverde.return_success_or_error_kt.core

data class SuccessReturn<R>(private val success: R) : ReturnSuccessOrError<R> {
    val result = success

    override fun toString(): String {
        return "SuccessReturn - $result"
    }
}