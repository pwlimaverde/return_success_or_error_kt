package com.pwlimaverde.return_success_or_error_kt.parameters

import com.pwlimaverde.return_success_or_error_kt.error.AppError
import com.pwlimaverde.return_success_or_error_kt.error.ErrorGeneric

data class NoParams(private val errorParams: AppError?) : ParametersReturnResult {
    override val error = errorParams ?: ErrorGeneric("Erro desconhecido")
}