package com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.response

import com.ceiba.capacitacion.mvvmpattern.shared.model.dataAccess.utils.Status

data class Resource<out T>(val status: Status, val data: T?, val code: Int?, val message: Int?) {
    companion object {
        fun <T> success(data: T?, code: Int? = null, message: Int? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, code, message)
        }

        fun <T> error(data: T?, code: Int, msg: Int?): Resource<T> {
            return Resource(Status.ERROR, data, code, msg)
        }

        fun <T> loading(data: T?, message: Int?): Resource<T> {
            return Resource(Status.LOADING, data, null, message)
        }
    }
}