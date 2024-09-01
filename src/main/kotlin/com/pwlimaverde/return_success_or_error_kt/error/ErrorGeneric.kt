package com.pwlimaverde.return_success_or_error_kt.error

class ErrorGeneric(message: String) : AppError(message) {
    override fun toString(): String {
        return "ErrorGeneric - $message"
    }
}