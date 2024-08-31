package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.AppError
import com.pwlimaverde.return_success_or_error_kt.error.ErrorGeneric
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class ErrorReturnTest {

    @Test
    fun getResult() {
        val resulBoolean = ErrorReturn<Boolean>(ErrorGeneric("Teste Error Boolean"))
        val resultString = ErrorReturn<String>(ErrorGeneric("Teste Error String"))
        assertEquals("Teste Error Boolean", resulBoolean.result.message)
        assertIs<AppError>(resulBoolean.result)
        assertIs<ErrorReturn<Boolean>>(resulBoolean)
        assertEquals("Teste Error String", resultString.result.message)
        assertIs<AppError>(resultString.result)
        assertIs<ErrorReturn<String>>(resultString)
    }
}