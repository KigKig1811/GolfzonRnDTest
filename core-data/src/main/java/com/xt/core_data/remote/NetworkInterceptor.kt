package com.xt.core_data.remote

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(

): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", "live_8U3Knxts1pkivrDKvmb8ZDlZajwqpZn1XuITrbeq7WxaUeHbFg1JsYevcwfp6f3Z") // Thay "x-api-key" bằng header key của API bạn
            .build()
        return chain.proceed(request)
    }
}
