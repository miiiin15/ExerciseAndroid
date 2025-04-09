package com.miiiin15.feature.home.data.remote.model

import com.miiiin15.feature.home.domain.model.LocalItem

data class LocalItemApiModel(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: String,
    val mapy: String,
)

fun LocalItemApiModel.toDomainModel() = LocalItem(
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