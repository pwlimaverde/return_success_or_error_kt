package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.ErrorGeneric
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertIs


class SuccessReturnTest {

    private fun testeReturn(): ReturnSuccessOrError<Boolean> {
        val testResult = true
        return if (testResult) {
            SuccessReturn(true)
        } else {
            ErrorReturn(ErrorGeneric("Erro desconhecido"))
        }

    }

    @Test
    fun getResult() {
        when (val teste = testeReturn()) {
            is SuccessReturn<Boolean> -> {
                assertEquals(true, teste.result)
                assertIs<Boolean>(teste.result)
                assertIs<SuccessReturn<Boolean>>(teste)
            }
            else -> {}
        }

        val resulBoolean = SuccessReturn(true)
        val resultString = SuccessReturn("teste string")
        assertEquals(true, resulBoolean.result)
        assertIs<Boolean>(resulBoolean.result)
        assertIs<SuccessReturn<Boolean>>(resulBoolean)
        assertEquals("teste string", resultString.result)
        assertIs<String>(resultString.result)
        assertIs<SuccessReturn<String>>(resultString)

    }
}