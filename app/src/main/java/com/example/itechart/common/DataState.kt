package com.example.itechart.common

sealed class DataState<T>(val metaData: MetaData? = null, val payload: T? = null) {
    class Loading<T>(metaData: LoadingMetaData? = null, payload: T? = null) :
        DataState<T>(metaData, payload)

    class Success<T>(metaData: SuccessMetaData? = null, payload: T? = null) :
        DataState<T>(metaData, payload)

    class Error<T>(metaData: ErrorMetaData? = null, payload: T? = null) :
        DataState<T>(metaData, payload)
}


sealed interface MetaData

//for extendability to use as SOLID principles
open class LoadingMetaData : MetaData
open class SuccessMetaData : MetaData
open class ErrorMetaData(exception: Exception? = null) : MetaData
