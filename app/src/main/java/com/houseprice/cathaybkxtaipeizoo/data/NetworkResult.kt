package com.houseprice.cathaybkxtaipeizoo.data

import com.houseprice.cathaybkxtaipeizoo.data.model.ErrorModel


sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T?) : NetworkResult<T>()
    class Error<T : Any>(var error: ErrorModel) : NetworkResult<T>()
}