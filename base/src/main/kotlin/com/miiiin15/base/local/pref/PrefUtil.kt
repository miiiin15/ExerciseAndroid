package com.miiiin15.base.local.pref

import com.miiiin15.base.local.pref.model.LikedLocalItem

interface PrefUtil {
    var likedLocalList: List<LikedLocalItem>?
    fun addLikedLocalItem(item : LikedLocalItem)
    fun removeLikedLocalItem(mapx: String)
}