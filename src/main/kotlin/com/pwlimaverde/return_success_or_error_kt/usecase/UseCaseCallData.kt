package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.datasource.DataSource
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult

abstract class UseCaseCallData<TypeUseCase, TypeDataSource, Parameters : ParametersReturnResult>(
    dataSource: DataSource<TypeDataSource, Parameters>,
) : Repository<TypeDataSource, Parameters>(dataSource) {
    abstract operator fun invoke(parameters: Parameters): ReturnSuccessOrError<TypeUseCase>
}