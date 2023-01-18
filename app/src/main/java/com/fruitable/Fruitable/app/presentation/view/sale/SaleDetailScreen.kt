package com.fruitable.Fruitable.app.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fruitable.Fruitable.R
import com.fruitable.Fruitable.app.domain.utils.sampleUrl
import com.fruitable.Fruitable.app.presentation.component.*
import com.fruitable.Fruitable.app.presentation.component._view.ResourceImage
import com.fruitable.Fruitable.app.presentation.navigation.Screen
import com.fruitable.Fruitable.app.presentation.viewmodel.sale.SaleDetailViewModel
import com.fruitable.Fruitable.ui.theme.MainGreen1
import com.fruitable.Fruitable.ui.theme.MainGreen2
import com.fruitable.Fruitable.ui.theme.TextStyles

@Composable
fun SaleDetailScreen(
    navController: NavController,
    saleId : Int = 1,
    viewModel: SaleDetailViewModel = hiltViewModel()
) {
    val saleDetail = viewModel.saleDetail.value.saleDetail
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                FruitableDivider()
                FruitableButton (
                    text = "주문하기",
                    color = MainGreen1,
                    textColor = Color.White,
                    modifier = Modifier.padding(30.dp, 14.dp, 30.dp, 30.dp),
                    onClick = { navController.navigate(Screen.SalesScreen.route) }
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { DetailTop(/*deadline = saleDetail.endDate, itemImageUrl = saleDetail.fileURL*/) }
            item { DetailFarmProfile(/*nickName = saleDetail.userId.name, phoneNum = saleDetail.contact*/) }
            item { DetailContent() }
            item { DetailHashTag() }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

@Composable
fun DetailHashTag(
    tags: List<String> = listOf("푸릇 농장", "제주 감귤", "귤", "제주도", "세계제일")
) {
    LazyRow(
        modifier = Modifier.padding(start = 22.dp, top = 20.dp, bottom = 19.dp)
    ) {
        item {
            tags.forEach {
                Row {
                    HashTagButton(text = "# $it", isRipple = false,)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    price: Int = 26900,
    title: String = "귤탐 당도선별 하우스 감귤 로열과",
    content: String = """ ❗️ 깨끗한 산지에서 재배한 감귤
 ❗ 탱글탱글한 과육과 새콤달콤한 과즙
 ❗ 비타민C, A 등을 함유한 과일
 ❗ 디저트, 주스 등의 요리로 활용"""
) {
    Column(
        modifier = Modifier
            .padding(start = 22.dp, top = 22.dp, bottom = 5.dp)
            .fillMaxWidth()
    ){
        Text(
            text = formatAmountOrMessage(price.toString()) +"원",
            style = TextStyles.TextHeavyBold,
        )
        Text( text = title, style = TextStyles.TextBold3)
        Text (
            text = content,
            modifier = Modifier.padding(20.dp, 18.dp, 0.dp, 5.dp),
            style = TextStyles.TextSmall3
        )
    }
}

@Composable
fun DetailTop(
    itemImageUrl: List<String> = (0 until 10).map { sampleUrl }
){
    Box(
        modifier = Modifier.height(300.dp).fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ){
        FruitableImage(
            imageUrl = if (itemImageUrl.isEmpty()) sampleUrl
                else itemImageUrl.first(),
            contentDescription = "sale_image",
            modifier = Modifier.fillMaxSize(),
            clip = RoundedCornerShape(0.dp)
        )
        HashTagButton(
            text = "D-$deadline",
            style = TextStyles.TextSmall3,
            modifier = Modifier.padding(top = 20.dp, end = 20.dp),
            isRipple = false,
        )
    }
}
@Composable
fun DetailFarmProfile(
    nickName : String = "푸릇농장",
    phoneNum : String = "051-456-5978",
){
    Column {
        Row (
            modifier = Modifier.padding(start = 20.dp,top = 18.dp , bottom=18.dp)
        ){
            ResourceImage(boxModifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MainGreen2))
            Column(modifier = Modifier.padding(start = 9.dp,top=7.dp)) {
                Text(
                    text = nickName,
                    style = TextStyles.TextBold2,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(text = phoneNum, style = TextStyles.TextSmall2,)
            }
        }
        FruitableDivider(modifier = Modifier.padding(28.dp,0.dp))
    }
}