package com.motiv8.quote.feature_quote.data.data_source.remote.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type,Call<NetworkResult<Type>>>{
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> {
        return NetworkResultCall(call)
    }
}

class NetworkResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != NetworkResult::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResultCallAdapter(resultType)
    }

    companion object {
        fun create(): NetworkResultCallAdapterFactory = NetworkResultCallAdapterFactory()
    }

}