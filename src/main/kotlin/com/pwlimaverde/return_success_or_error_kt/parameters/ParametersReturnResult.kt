package com.pwlimaverde.return_success_or_error_kt.parameters

import com.pwlimaverde.return_success_or_error_kt.error.AppError

interface ParametersReturnResult {
    val error: AppError
}