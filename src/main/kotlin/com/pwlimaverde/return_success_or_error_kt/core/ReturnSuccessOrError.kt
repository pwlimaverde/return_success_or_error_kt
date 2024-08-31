package com.pwlimaverde.return_success_or_error_kt.core

import com.pwlimaverde.return_success_or_error_kt.error.AppError

sealed class ReturnSuccessOrError<R>(error: AppError?, success: R?)