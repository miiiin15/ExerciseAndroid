package com.miiiin15.feature.home.data.remote.response

import com.miiiin15.feature.home.data.remote.model.LocalItemApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchLocalResponse(
   @SerialName("items") val items: List<LocalItemApiModel>
)