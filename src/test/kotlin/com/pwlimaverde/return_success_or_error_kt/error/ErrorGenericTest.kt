package com.pwlimaverde.return_success_or_error_kt.error

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class ErrorGenericTest {

    private val result = ErrorGeneric("teste")

    @Test
    fun getMessage() {
        assertEquals("teste", result.message)
    }
    @Test
    fun getType() {
        assertIs<AppError>(result)
    }
}