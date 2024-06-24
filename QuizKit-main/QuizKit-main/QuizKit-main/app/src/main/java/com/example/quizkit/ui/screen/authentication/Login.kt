package com.example.quizkit.ui.screen.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quizkit.R
import com.example.quizkit.data.common.Screen
import com.example.quizkit.ui.component.InputForm
import com.example.quizkit.ui.component.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = koinViewModel()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(navBackStackEntry = navBackStackEntry, navigateBack = {
                navController.popBackStack()
            })
        },
        containerColor = Color(0xFFFFD180),
        contentColor = Color.Black
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 30.dp)
        ) {
            InputForm(title = "Alamat Email", value = email, icon = R.drawable.email, onChange = {
                email = it
            })
            InputForm(title = "Kata Sandi", value = password, icon = R.drawable.lock, onChange = {
                password = it
            })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBF360C),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Masuk", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append("Belum punya akun? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFBF360C),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Daftar di sini")
                    }

                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.Register.route)
                    }
                    .padding(top = 10.dp)
            )
        }

        val alert = authViewModel.loginThread.value

        if (alert.loading) {
            Toast.makeText(context, "Sedang memuat", Toast.LENGTH_SHORT).show()
        } else {
            alert.data?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                authViewModel.saveToken(email)
                navController.navigate(Screen.Main.route)
            }
            alert.error?.let { error ->
                Log.e("error", error)
            }
        }
    }
}
