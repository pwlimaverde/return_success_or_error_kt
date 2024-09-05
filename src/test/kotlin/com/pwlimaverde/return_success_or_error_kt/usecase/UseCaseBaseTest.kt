package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ErrorReturn
import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.core.SuccessReturn
import com.pwlimaverde.return_success_or_error_kt.mock.ErrorTest
import com.pwlimaverde.return_success_or_error_kt.mock.ParametersTeste
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class TestUseCaseBase() : UseCaseBase<Boolean, ParametersTeste> {
    override fun invoke(parameters: ParametersTeste): ReturnSuccessOrError<Boolean> {
        try {
            if (parameters.boolean) {
                return SuccessReturn(true)
            }
            return ErrorReturn(parameters.error)
        } catch (e: Exception) {
            return ErrorReturn(ErrorTest("Erro ao retornar valor - ${e.message}"))
        }
    }
}
class UseCaseBaseTest {

    @Test
    fun getResult() {
        val useCase = TestUseCaseBase()
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn<Boolean> -> {
                println("getResult final result ${data.result}")
                assertEquals(true, data.result)
            }
            else -> {}
        }
    }
    @Test
    fun getResultError() {
        val useCase = TestUseCaseBase()
        when (val data = useCase(ParametersTeste(ErrorTest("teste"), false))) {
            is ErrorReturn<Boolean> -> {
                assertEquals("teste", data.result.message)
            }
            else -> {}
        }
    }

    @Test
    fun getResultCoroutine(): Unit = runTest {
        val useCase = TestUseCaseBase()
        println("getResultCoroutine inicial - Thread: ${Thread.currentThread().name}")
        when (val data = useCase.invokeCoroutine(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn<Boolean> -> {
                println("getResultCoroutine final result ${data.result} - Thread: ${Thread.currentThread().name}")
                assertEquals(true, data.result)
            }
            else -> {}
        }
    }
    @Test
    fun getResultCoroutineError(): Unit = runTest {
        val useCase = TestUseCaseBase()
        when (val data = useCase.invokeCoroutine(ParametersTeste(ErrorTest("teste"), false))) {
            is ErrorReturn<Boolean> -> {
                assertEquals("teste", data.result.message)
            }
            else -> {}
        }
    }

    @Test
    fun getResultThread(): Unit = runTest {
        val useCase = TestUseCaseBase()
        println("getResultThread inicial - Thread:${Thread.currentThread().name}")
        when (val data = useCase.invokeThread(ParametersTeste(ErrorTest("teste"), true))) {
            is SuccessReturn<Boolean> -> {
                println("getResultThread final result ${data.result} - Thread:${Thread.currentThread().name}")
                assertEquals(true, data.result)
            }
            else -> {}
        }
    }
    @Test
    fun getResultThreadError(): Unit = runTest {
        val useCase = TestUseCaseBase()
        when (val data = useCase.invokeThread(ParametersTeste(ErrorTest("teste"), false))) {
            is ErrorReturn<Boolean> -> {
                assertEquals("teste", data.result.message)
            }
            else -> {}
        }
    }
}