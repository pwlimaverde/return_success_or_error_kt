package com.pwlimaverde.return_success_or_error_kt.mock

import com.pwlimaverde.return_success_or_error_kt.datasource.DataSource
import com.pwlimaverde.return_success_or_error_kt.error.AppError
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult

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

data class ParametersTeste(override var error: AppError, val boolean: Boolean) :
    ParametersReturnResult

data class TestParams(override var error: AppError, val nome: String, val idade: Int) :
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