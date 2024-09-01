package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ErrorReturn
import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.core.SuccessReturn
import com.pwlimaverde.return_success_or_error_kt.datasource.DataSource
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

data class ParametersTeste(override var error: AppError, val boolean: Boolean) :
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

class TestUseCase(
    dataSource: DataSource<Boolean, ParametersTeste>,
) : UseCaseCallData<String, Boolean, ParametersTeste>(dataSource) {
    override fun invoke(parameters: ParametersTeste): ReturnSuccessOrError<String> {
        return when (val data = resultDatasource(parameters)) {
            is SuccessReturn<Boolean> -> {
                SuccessReturn("Success data ${data.result}")
            }
            is ErrorReturn<Boolean> -> {
                ErrorReturn(data.result)
            }
        }
    }
}

class UseCaseCallDataTest {

    @Test
    fun resultDatasource() {
        val useCase = TestUseCase(
            TesteDataSource(ExternalMock()),
        )
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn<String> -> {
                println("teste use case ${data.result}")
                assertEquals("Success data true", data.result)
            }
            is ErrorReturn<String> -> {
                assertEquals("teste", data.result.message)
            }

        }
    }
    @Test
    fun resultDatasourceError() {
        val useCase = TestUseCase(
            TesteDataSource(ExternalMock()),
        )
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), false))) {
            is SuccessReturn<String> -> {
                println("teste use case ${data.result}")
                assertEquals("Success data true", data.result)
            }
            is ErrorReturn<String> -> {
                assertEquals("teste - catch RepositoryErro ao retornar valor", data.result.message)
            }

        }
    }
}