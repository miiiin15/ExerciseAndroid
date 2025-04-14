package com.miiiin15.feature.home.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.miiiin15.feature.Constants
import com.miiiin15.feature.home.domain.model.LocalItem

fun String.removeHtmlTags(): String =
    this.replace(Regex("<.*?>"), "")

@Composable
fun LocalLazyGrid(
    scrollState: LazyGridState = rememberLazyGridState(),
    localItem: List<LocalItem>,
    onLikedButtonClick: (Boolean, LocalItem?) -> Unit
) {
    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        itemsIndexed(localItem, key = { index, item -> "${item.mapx}_$index" }) { _, item ->
            LocalLazyColumnItem(item, onLikedButtonClick)
        }
    }
}

@Composable
fun LocalLazyColumnItem(
    item: LocalItem,
    onLikedButtonClick: (Boolean, LocalItem?) -> Unit,
) {
    var isLiked by remember { mutableStateOf(item.isLiked) }
    var expanded by remember { mutableStateOf(false) }
    val imageWidthSize by animateDpAsState(targetValue = if (expanded) 90.dp else 64.dp, label = "")
    val imageHeightSize by animateDpAsState(
        targetValue = if (expanded) 90.dp else 64.dp,
        label = ""
    )


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .size(width = 180.dp, height = 180.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = Constants.imageBaseUrl + item.title.removeHtmlTags()),
                contentDescription = item.title.removeHtmlTags(),
                modifier = Modifier
                    .size(width = imageWidthSize, height = imageHeightSize)
                    .clickable { expanded = !expanded },
                contentScale = ContentScale.Crop
            )
            IconButton(onClick = {
                onLikedButtonClick(!isLiked, item)
                isLiked = !isLiked
            }) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isLiked) "좋아요 취소" else "좋아요",
                    tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }

        }
        Text(item.title.removeHtmlTags())
        Row(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(8.dp)
        ) {
            Text(item.category)
        }
    }
}


@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewLocalLazyColumn() {
    val sampleItems = listOf(
        LocalItem(
            title = "정선할매곤드레<b>밥</b> 프레스센터",
            link = "",
            category = "음식점>한식",
            description = "",
            telephone = "",
            address = "서울특별시 중구 태평로1가 25 지하아케이드",
            roadAddress = "서울특별시 중구 세종대로 124 지하아케이드",
            mapx = "1269778908",
            mapy = "375674232"
        ),
        LocalItem(
            title = "꽃<b>밥</b>에피다",
            link = "https://app.catchtable.co.kr/ct/shop/flower_blossom_on_the_rice?from=share&type=VISIT_RESERVATION",
            category = "음식점>한식",
            description = "",
            telephone = "",
            address = "서울특별시 종로구 관훈동 118-27",
            roadAddress = "서울특별시 종로구 인사동16길 3-6",
            mapx = "1269840246",
            mapy = "375753027"
        ),
        LocalItem(
            title = "<b>밥</b>도사술도사",
            link = "",
            category = "음식점>한식",
            description = "",
            telephone = "",
            address = "서울특별시 종로구 낙원동 233 1층",
            roadAddress = "서울특별시 종로구 종로17길 46 1층",
            mapx = "1269883692",
            mapy = "375721890"
        ),
        LocalItem(
            title = "힛더<b>밥</b>",
            link = "",
            category = "음식점>한식",
            description = "",
            telephone = "",
            address = "서울특별시 종로구 견지동 110 1층 109호",
            roadAddress = "서울특별시 종로구 삼봉로 95 1층 109호",
            mapx = "1269827936",
            mapy = "375723129"
        ),
        LocalItem(
            title = "청국장<b>밥</b>",
            link = "",
            category = "음식점>한식",
            description = "",
            telephone = "",
            address = "서울특별시 종로구 소격동 36 1층",
            roadAddress = "서울특별시 종로구 삼청로2길 29-5 1층",
            mapx = "1269816301",
            mapy = "375804973"
        )
    )
    LocalLazyGrid(
        scrollState = rememberLazyGridState(),
        localItem = sampleItems,
        onLikedButtonClick = { _, _ -> }
    )
}