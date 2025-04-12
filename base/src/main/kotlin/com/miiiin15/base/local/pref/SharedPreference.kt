package com.miiiin15.base.local.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

open class SharedPreference @Inject constructor(@ApplicationContext context: Context) {
    val sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun contains(vararg keys: String): Boolean = keys.all(sharedPreference::contains)

    fun remove(vararg keys: String): Boolean =
        sharedPreference.edit().apply { keys.forEach { key -> remove(key) } }.commit()

    fun clearAll() {
        sharedPreference.edit().clear().apply()
    }

    fun put(key: String, value: Any) = sharedPreference.edit().run {
        when (value) {
            is String -> putString(key, value)
            // TODO :  다른 자료형도 지원하기
            else -> putString(key, value.toJson())
        }
    }.commit()

    inline fun <reified T> get(key: String): T? {
        val json = sharedPreference.getString(key, null) ?: return null
        val result = Gson.fromJson<T>(json, object : TypeToken<T>() {}.type)

        return if (result is List<*>) {
            result.filterNotNull() as? T
        } else {
            result
        }
    }
}