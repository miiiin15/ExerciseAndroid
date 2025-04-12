package com.miiiin15.feature.home.data.mapper

import com.miiiin15.base.local.pref.model.LikedLocalItem
import com.miiiin15.feature.home.domain.model.LocalItem

fun LikedLocalItem.toDomain(): LocalItem = LocalItem(
    title = title,
    link = link,
    category = category,
    description = description,
    telephone = telephone,
    address = address,
    roadAddress = roadAddress,
    mapx = mapx,
    mapy = mapy
)

fun LocalItem.toLocal(): LikedLocalItem = LikedLocalItem(
    title = title,
    link = link,
    category = category,
    description = description,
    telephone = telephone,
    address = address,
    roadAddress = roadAddress,
    mapx = mapx,
    mapy = mapy
)