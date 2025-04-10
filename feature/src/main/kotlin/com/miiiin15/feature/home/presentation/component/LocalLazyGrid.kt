package com.miiiin15.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miiiin15.feature.home.domain.model.LocalItem

fun String.removeHtmlTags(): String =
    this.replace(Regex("<.*?>"), "")

@Composable
fun LocalLazyGrid(
    localItem: List<LocalItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(8.dp)
    ) {
        items(localItem, key = { it.mapx }) { item ->
            LocalLazyColumnItem(item)
        }
    }
}

@Composable
fun LocalLazyColumnItem(
    item: LocalItem
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).size(width = 140.dp, height = 140.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(modifier = Modifier.background(Color.Red).size(width = 48.dp, height = 48.dp)){}
        Text(
            item.title.removeHtmlTags())
        Box(
            modifier = Modifier
                .background(color = androidx.compose.ui.graphics.Color.LightGray)
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
    LocalLazyGrid(localItem = sampleItems)
}