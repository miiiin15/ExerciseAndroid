package com.miiiin15.base.local.pref

import com.google.gson.GsonBuilder

val Gson = GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create()!!

fun Any.toJson(): String = Gson.toJson(this)

inline fun <reified T> String.fromJson(): T? =
    runCatching {
        Gson.fromJson(this, T::class.java)
    }.onFailure {
        println("[${T::class.java.name}] json 파싱 오류 발생 - ${it.message} : $this")
    }.getOrNull()