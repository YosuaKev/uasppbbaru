package com.example.quizkit.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.ui.component.CardQuiz
import com.example.quizkit.ui.theme.Red40
import com.example.quizkit.ui.theme.Red80
import com.example.quizkit.ui.theme.RedGrey80
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateToQuiz: (String) -> Unit,
    navigateToCategory: () -> Unit,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    Column {
        Column(Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            RecentBoard(homeViewModel, navigateToQuiz, navigateToCategory)
            Spacer(modifier = Modifier.height(16.dp))
            PlayBoard(navigateToCategory)
        }
        PopularList(navigateToQuiz, navigateToCategory)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentBoard(
    homeViewModel: HomeViewModel,
    navigateToQuiz: (String) -> Unit,
    navigateToCategory: () -> Unit
) {
    homeViewModel.getLatestHistory().collectAsState(initial = null).value.let { history ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE4EAFF),
            ),
            onClick = {
                if (history != null) navigateToQuiz(history.category) else
                    navigateToCategory()
            }
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = if (history != null) "Kuis Terbaru" else "Ayo Main Kuis",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                    if (history != null) {
                        Text(
                            text = "${history.quiz} - ${history.category}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Red40
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "recent-play",
                    modifier = Modifier.size(30.dp),
                    tint = Red40
                )
            }
        }
    }
}

@Composable
fun PlayBoard(navigateToCategory: () -> Unit) {
    Surface(color = Color(0x4DF1F4FF), shape = RoundedCornerShape(12)) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Jelajahi perjalanan penemuan bersama, di mana tantangan meningkatkan pengetahuan secara luas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = navigateToCategory,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Red40
                )
            ) {
                Text(text = "Ayo Main", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PopularList(navigateToQuiz: (String) -> Unit, navigateToCategory: () -> Unit) {
    Surface(
        Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 16, topEndPercent = 16)
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .padding(top = 16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Kuis Populer",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
                Text(
                    text = "Lihat Semua",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.text_purple),
                    modifier = Modifier.clickable { navigateToCategory() }
                )
            }
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                CardQuiz("Pendidikan", "Geografi", navigateToQuiz)
                CardQuiz("Hiburan", "Video Games", navigateToQuiz)
            }
        }
    }
}
