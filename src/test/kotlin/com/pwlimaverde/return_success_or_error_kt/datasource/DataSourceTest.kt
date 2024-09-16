package com.pwlimaverde.return_success_or_error_kt.datasource

import com.pwlimaverde.return_success_or_error_kt.mock.ErrorTest
import com.pwlimaverde.return_success_or_error_kt.mock.ExternalMock
import com.pwlimaverde.return_success_or_error_kt.mock.ParametersTeste
import com.pwlimaverde.return_success_or_error_kt.mock.TesteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith


class DataSourceTest {

    @Test
    fun getDataSource(): Unit = runTest {
        val result = TesteDataSource(ExternalMock())
        val data = result(ParametersTeste(ErrorTest("teste"), true))
        assertEquals(true, data)
    }

    @Test
    fun getDataSourceError2() = runTest {
        assertFailsWith<Exception> {
            val result = TesteDataSource(ExternalMock())
            val data = result(ParametersTeste(ErrorTest("teste"), false))
            println(data)
        }
    }
}