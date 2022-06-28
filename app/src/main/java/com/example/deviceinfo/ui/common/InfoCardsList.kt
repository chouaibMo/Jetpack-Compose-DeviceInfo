package com.example.deviceinfo.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deviceinfo.model.Data

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoCardsList(list: List<Data>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp),
    ) {
        itemsIndexed(items = list) { _, item ->
            InfoItem(item)
        }
    }
}

@Composable
fun InfoItem(data: Data) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxSize()
        ) {
            Image(
                imageVector = data.icon,
                contentDescription = data.label,
                colorFilter = ColorFilter.tint(Color.DarkGray),
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = data.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = data.label,
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }
    }

}