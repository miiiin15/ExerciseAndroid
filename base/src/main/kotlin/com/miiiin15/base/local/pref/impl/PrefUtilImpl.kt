package com.miiiin15.base.local.pref.impl

import android.app.Application
import com.miiiin15.base.local.pref.PrefUtil
import com.miiiin15.base.local.pref.SharedPreference
import com.miiiin15.base.local.pref.model.LikedLocalItem
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PrefUtilImpl @Inject constructor(application: Application): PrefUtil{
    private val sharedPreferences = SharedPreference(application)

    override var likedLocalList : List<LikedLocalItem>?
        get() = sharedPreferences.get(PREF_LIKED_LOCAL_LIST)
        set(value) {
            if (value != null) {
                sharedPreferences.put(PREF_LIKED_LOCAL_LIST, value)
            }
        }

    override fun addLikedLocalItem(item: LikedLocalItem) {
        val currentList = likedLocalList?.toMutableList() ?: mutableListOf()
        if (!currentList.any { it.mapx == item.mapx }) {
            currentList.add(item)
            likedLocalList = currentList
        }
    }

    override fun removeLikedLocalItem(mapx: String) {
        val currentList = likedLocalList?.toMutableList() ?: mutableListOf()
        val itemToRemove = currentList.find { it.mapx == mapx }
        if (itemToRemove != null) {
            currentList.remove(itemToRemove)
            likedLocalList = currentList
        }
    }

    companion object{
        private const val PREF_LIKED_LOCAL_LIST = "PREF_LIKED_LOCAL_LIST"
    }
}
