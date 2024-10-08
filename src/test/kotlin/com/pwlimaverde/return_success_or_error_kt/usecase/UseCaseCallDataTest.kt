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
    override suspend fun invoke(parameters: ParametersTeste): ReturnSuccessOrError<String> {
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
    fun getResultData(): Unit = runTest {
        val useCase = TestUseCaseCallData(TesteDataSource(ExternalMock()))
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn<String> -> {
                println("getResultData result ${data.result}")
                assertEquals("Success data true", data.result)
            }
            is ErrorReturn<String> -> println("getResultData error ${data.result}")
        }
    }

    @Test
    fun getResultDataError(): Unit = runTest {
        val useCase = TestUseCaseCallData(
            TesteDataSource(ExternalMock()),
        )
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), false))) {
            is SuccessReturn<String> -> println("getResultDataError ${data.result}")
            is ErrorReturn<String> -> {
                assertEquals(
                    "teste - catch Repository Erro ao retornar valor",
                    data.result.message
                )
            }
        }
    }

    @Test
    fun getResultCoroutineData(): Unit = runTest {
        val useCase = TestUseCaseCallData(TesteDataSource(ExternalMock()))
        println("getResultCoroutineData Thread inicial: ${Thread.currentThread().name}")
        when (val data = useCase.invokeCoroutine(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn -> {
                println("getResultCoroutineData Thread final: ${Thread.currentThread().name}")
                println("getResultCoroutineData - result ${data.result}")
                assertEquals("Success data true", data.result)
            }
            is ErrorReturn -> println("getResultCoroutineData error ${data.result}")
        }
    }

    @Test
    fun getResultCoroutineDataError(): Unit = runTest {
        val useCase = TestUseCaseCallData(
            TesteDataSource(ExternalMock()),
        )
        when (val data = useCase.invokeCoroutine(ParametersTeste(ErrorTest("teste"), false))) {
            is SuccessReturn<String> -> assertEquals("Success data true", data.result)
            is ErrorReturn<String> -> {
                assertEquals("teste - catch Repository Erro ao retornar valor", data.result.message)
            }
        }
    }
}