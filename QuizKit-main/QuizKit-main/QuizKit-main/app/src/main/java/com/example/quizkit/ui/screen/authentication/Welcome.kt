package com.example.quizkit.ui.screen.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFD180)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.welcome), contentDescription = "Selamat Datang")
        Column(
            Modifier
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(10))
                .background(color = Color.White)
        ) {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color(0xFFBF360C)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    text = "Masuk atau buat akun untuk mengikuti kuis, ikut tantangan, dan banyak lagi."
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(Screen.Login.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7043),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Masuk", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate(Screen.Register.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBF360C),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Buat Akun", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
