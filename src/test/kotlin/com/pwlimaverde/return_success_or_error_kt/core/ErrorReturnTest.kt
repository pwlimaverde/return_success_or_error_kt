package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.AppError
import com.pwlimaverde.return_success_or_error_kt.error.ErrorGeneric
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class ErrorReturnTest {

    private fun testeReturn(): ReturnSuccessOrError<Boolean> {
        val testResult = false
        return if (testResult) {
            SuccessReturn(true)
        } else {
            ErrorReturn(ErrorGeneric("Erro desconhecido"))
        }

    }

    @Test
    fun getResult() {
        when (val teste = testeReturn()) {
            is ErrorReturn -> {
                assertEquals("Erro desconhecido", teste.result.message)
                assertIs<AppError>(teste.result)
                assertIs<ErrorReturn<Boolean>>(teste)
            }
            else -> {}
        }
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