package com.pwlimaverde.return_success_or_error_kt.error

abstract class AppError(override var message: String) : Exception(message){
    abstract override fun toString(): String
}