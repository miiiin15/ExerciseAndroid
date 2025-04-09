package com.miiiin15.base.remote

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject
import javax.inject.Named
import kotlin.jvm.Throws

class RequestHeaderInterceptor @Inject constructor(
    @Named("clientId") private val clientId: String,
    @Named("clientSecret") private val clientSecret: String
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = try {
            chain.request().newBuilder()
                .apply {
                    addHeader("X-Naver-Client-Id", clientId)
                    addHeader("X-Naver-Client-Secret", clientSecret)
                }.build()
        } catch (e: Exception) {
            println("Error in RequestHeaderInterceptor: ${e.message}")
            chain.request()
        }
        return chain.proceed(newRequest)
    }
}