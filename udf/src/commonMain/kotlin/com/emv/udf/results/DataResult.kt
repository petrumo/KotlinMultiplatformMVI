package com.emv.udf.results

sealed class DataResult<T>  {
    class Loading<T> : DataResult<T>()
    data class Success<T>(val data: T) : DataResult<T>()
    data class Failure<T>(val error: String) : DataResult<T>()
}