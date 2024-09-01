package com.pwlimaverde.return_success_or_error_kt.datasource

import com.pwlimaverde.return_success_or_error_kt.error.AppError
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class ExternalMock() {
    fun returnBoolean(test: Boolean?): Boolean {
        if (test != null) {
            return test
        } else {
            throw Exception("Erro ao retornar valor")
        }
    }
}

class ErrorTest(message: String) : AppError(message) {
    override fun toString(): String {
        return "ErrorTest - $message"
    }
}

data class ParametersTeste(override val error: AppError, val boolean: Boolean) :
    ParametersReturnResult

class TesteDataSource(private val externalMock: ExternalMock) :
    DataSource<Boolean, ParametersTeste> {
    override fun invoke(parameters: ParametersTeste): Boolean {
        try {
            if (parameters.boolean) {
                return externalMock.returnBoolean(true)
            }
            return externalMock.returnBoolean(null)
        } catch (e: Exception) {
            println("Erro data $e")
            throw e
        }
    }

}


class DataSourceTest {

    @Test
    fun getDataSource() {
        val result = TesteDataSource(ExternalMock())
        val data = result(ParametersTeste(ErrorTest("teste"), true))
        assertEquals(true, data)
    }

    @Test
    fun getDataSourceError() {
        assertThrows(Exception::class.java) {
            val result = TesteDataSource(ExternalMock())
            val data = result(ParametersTeste(ErrorTest("teste"), false))
            println(data)
        }
    }
}