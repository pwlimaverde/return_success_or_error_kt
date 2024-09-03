package com.pwlimaverde.return_success_or_error_kt.datasource

import com.pwlimaverde.return_success_or_error_kt.mock.ErrorTest
import com.pwlimaverde.return_success_or_error_kt.mock.ExternalMock
import com.pwlimaverde.return_success_or_error_kt.mock.ParametersTeste
import com.pwlimaverde.return_success_or_error_kt.mock.TesteDataSource
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test


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