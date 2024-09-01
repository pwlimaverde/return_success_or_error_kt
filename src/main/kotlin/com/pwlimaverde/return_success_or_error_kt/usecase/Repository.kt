package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ErrorReturn
import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.core.SuccessReturn
import com.pwlimaverde.return_success_or_error_kt.datasource.DataSource
import com.pwlimaverde.return_success_or_error_kt.error.AppError
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult

sealed class Repository<TypeDataSource, Parameters : ParametersReturnResult>(
    private val dataSource: DataSource<TypeDataSource, Parameters>,
) {
    fun resultDatasource(parameters: Parameters): ReturnSuccessOrError<TypeDataSource> {
        val messageError = parameters.error.message
        try {
            val result = dataSource(parameters)
            return SuccessReturn(result)
        } catch (e: Exception) {
            val error = parameters.error
            error.message = "$messageError - catch Repository${e.message}"
            return ErrorReturn(error)
        }
    }
}