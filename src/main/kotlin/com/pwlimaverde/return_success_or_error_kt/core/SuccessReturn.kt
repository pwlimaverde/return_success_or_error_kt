package com.pwlimaverde.return_success_or_error_kt.core


class SuccessReturn<R>(success: R) : ReturnSuccessOrError<R>(null, success) {
    val result = success
    override fun toString(): String {
        return "SuccessReturn - $result"
    }
}