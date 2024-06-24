package com.example.quizkit.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding(navController: NavController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        3
    }

    var imagePainter = remember { 0 }
    var heightImage = remember { 0 }
    var title = remember { "" }
    var description = remember { "" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFFFE0B2), Color(0xFFFFCC80))))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        HorizontalPager(state = pagerState) {
            when (pagerState.currentPage) {
                0 -> {
                    imagePainter = R.drawable.onboarding0
                    heightImage = 300
                    title = "Selamat Datang di EduQuiz"
                    description = "Jelajahi pengetahuan bersama kami! Mulailah menjawab pertanyaan menarik untuk memperluas wawasan Anda!"
                }

                1 -> {
                    imagePainter = R.drawable.onboarding1
                    heightImage = 320
                    title = "Buka Daya Otak Anda"
                    description = "Bergabunglah dengan kami untuk membuka misteri pengetahuan bersama. Menyelam ke dalam pertanyaan dan temukan wawasan baru!"
                }

                2 -> {
                    imagePainter = R.drawable.onboarding2
                    heightImage = 320
                    title = "Siap untuk Tantangan"
                    description = "Masuk ke dunia pengetahuan tanpa batas! Jawab pertanyaan, dapatkan poin, dan lihat sejauh mana Anda bisa pergi!"
                }
            }
            OnBoardingItem(imagePainter = imagePainter, heightImage = heightImage, title = title, description = description)
        }
        if (pagerState.currentPage == 2) {
            Button(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .padding(horizontal = 10.dp),
                onClick = {
                    navController.navigate(Screen.Welcome.route)
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBF360C),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Mulai Sekarang", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
        } else {
            IndicatorNavigation(pagerState, scope)
        }
    }
}

@Composable
fun OnBoardingItem(imagePainter: Int, heightImage: Int, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagePainter),
            contentDescription = title,
            modifier = Modifier.height((heightImage).dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFBF360C)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(0.85f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndicatorNavigation(pagerState: PagerState, scope: CoroutineScope) {
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(3) {
            val selected = pagerState.currentPage == it
            Box(
                modifier = Modifier
                    .size(if (selected) 14.dp else 10.dp)
                    .clip(shape = CircleShape)
                    .background(
                        color = if (selected) Color(0xFFBF360C) else Color.Gray,
                    )
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
