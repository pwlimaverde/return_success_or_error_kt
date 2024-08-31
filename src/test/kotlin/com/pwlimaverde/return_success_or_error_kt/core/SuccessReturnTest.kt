package com.pwlimaverde.return_success_or_error_kt.core

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class SuccessReturnTest {

    @Test
    fun getResult() {
        val resulBoolean = SuccessReturn<Boolean>(true)
        val resultString = SuccessReturn<String>("teste string")
        assertEquals(true, resulBoolean.result)
        assertIs<Boolean>(resulBoolean.result)
        assertIs<SuccessReturn<Boolean>>(resulBoolean)
        assertEquals("teste string", resultString.result)
        assertIs<String>(resultString.result)
        assertIs<SuccessReturn<String>>(resultString)

    }
}