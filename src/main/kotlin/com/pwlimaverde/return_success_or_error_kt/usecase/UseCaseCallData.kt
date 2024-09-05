package com.pwlimaverde.return_success_or_error_kt.usecase

import com.pwlimaverde.return_success_or_error_kt.core.ReturnSuccessOrError
import com.pwlimaverde.return_success_or_error_kt.datasource.DataSource
import com.pwlimaverde.return_success_or_error_kt.parameters.ParametersReturnResult
import kotlinx.coroutines.suspendCancellableCoroutine

abstract class UseCaseCallData<TypeUseCase, TypeDataSource, Parameters : ParametersReturnResult>(
    dataSource: DataSource<TypeDataSource, Parameters>,
):  UseCaseBase<TypeUseCase, Parameters>, Repository<TypeDataSource, Parameters>(dataSource)