package com.pwlimaverde.return_success_or_error_kt.datasource

import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult

interface DataSource<TypeDataSource, Parameters:ParametersReturnResult> {
    operator fun invoke(parameters: Parameters): TypeDataSource
}