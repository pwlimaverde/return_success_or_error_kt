package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ErrorReturn
import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.core.SuccessReturn
import com.pwlimaverde.return_success_or_error_kt.datasource.DataSource
import com.pwlimaverde.return_success_or_error_kt.mock.ErrorTest
import com.pwlimaverde.return_success_or_error_kt.mock.ExternalMock
import com.pwlimaverde.return_success_or_error_kt.mock.ParametersTeste
import com.pwlimaverde.return_success_or_error_kt.mock.TesteDataSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test



class TestUseCaseCallData(
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
    fun getResult() {
        val useCase = TestUseCaseCallData(TesteDataSource(ExternalMock()))
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn<String> -> {
                println("teste use case ${data.result}")
                assertEquals("Success data true", data.result)
            }

            is ErrorReturn<String> -> println("teste use case error ${data.result}")

        }
    }


    @Test
    fun getResultNewThread(): Unit = runTest {
        val useCase = TestUseCaseCallData(TesteDataSource(ExternalMock()))
        when (val data = useCase.invokeThread(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn -> {
                println("teste use case Thread ${data.result}")
                assertEquals("Success data true", data.result)
            }
            is ErrorReturn -> println("teste use case error ${data.result}")
        }

    }


    @Test
    fun getResultError() {
        val useCase = TestUseCaseCallData(
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

    @Test
    fun getResultNewThreadError(): Unit = runTest {
        val useCase = TestUseCaseCallData(
            TesteDataSource(ExternalMock()),
        )
        when (val data = useCase.invokeThread(ParametersTeste(ErrorTest("teste"), false))) {
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