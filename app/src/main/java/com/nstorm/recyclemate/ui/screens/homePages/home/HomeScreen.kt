package com.nstorm.recyclemate.ui.screens.homePages.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nstorm.recyclemate.R

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            RecycleScoreCard()
            Materials()
            TipsAndTricks()
        }
    }
}

@Composable
fun RecycleScoreCard(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(.60f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "500g",
                fontSize = 65.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "saved C02",
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 30.dp)
                .width(2.dp)
        )

        Column(
            modifier = Modifier
                .weight(.40f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "344",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(text = "points")

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "44",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(text = "recycles")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    painter = painterResource(id = R.drawable.userprofile),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Hi, ",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
                Text(
                    text = "Pranneth",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                painter = painterResource(id = R.drawable.view_cozy),
                contentDescription = ""
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Materials() {
    val state = rememberPagerState(pageCount = { 4 })
    val materials = mapOf(
        R.drawable.paper to "Paper",
        R.drawable.plastic to "Plastic",
        R.drawable.leather to "Leather",
        R.drawable.glass to "Glass",
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Materials",
            fontWeight = FontWeight.Bold,
        )
        HorizontalPager(
            state = state,
            pageSize = PageSize.Fixed(130.dp)
        ) { page: Int ->
            val material = materials.keys.elementAt(page)
            val materialName = materials[material]!!
            Column {
                Image(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .height(110.dp)
                        .width(100.dp),
                    painter = painterResource(id = material),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    modifier = Modifier
                        .offset(x = (-15).dp)
                        .fillMaxWidth(),
                    text = materialName,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TipsAndTricks() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 18.dp)
            .height(200.dp)
    ) {
        Text(
            text = "Tips and tricks",
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(
                    topEnd = 30.dp,
                    topStart = 10.dp,
                    bottomEnd = 30.dp,
                    bottomStart = 30.dp
                ),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .size(60.dp),
                        painter = painterResource(id = R.drawable.recycle_logo),
                        contentDescription = "",
                    )
                    Text(text = "Hello WOrld?")
                }
            }

            Spacer(modifier = Modifier.width(18.dp))

            Card(
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 30.dp,
                    bottomEnd = 30.dp,
                    bottomStart = 30.dp
                ),
                colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .size(60.dp),
                        painter = painterResource(id = R.drawable.recycle_logo),
                        contentDescription = "",
                    )
                    Text(text = "Hello WOrld?")
                }

            }
        }
    }
}