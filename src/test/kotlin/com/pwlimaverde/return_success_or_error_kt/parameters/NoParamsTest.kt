package com.pwlimaverde.return_success_or_error_kt.parameters

import com.pwlimaverde.return_success_or_error_kt.error.ErrorGeneric
import com.pwlimaverde.return_success_or_error_kt.mock.ErrorTest
import com.pwlimaverde.return_success_or_error_kt.mock.TestParams
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class NoParamsTest {

    @Test
    fun getErrorGeneric() {
        val noParams = NoParams(null)
        assertEquals("Erro desconhecido", noParams.error.message)
        assertIs<ErrorGeneric>(noParams.error)
    }

    @Test
    fun getErrorTest() {
        val noParams = NoParams(ErrorTest("teste"))
        assertEquals("teste", noParams.error.message)
        assertIs<ErrorTest>(noParams.error)
    }

    @Test
    fun getParameters() {
        val testParams = TestParams(ErrorGeneric("teste"), "teste", 10)
        assertEquals("teste", testParams.error.message)
        assertIs<ErrorGeneric>(testParams.error)
        assertEquals("teste", testParams.nome)
        assertEquals(10, testParams.idade)
    }
}