package com.example.quizkit.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizkit.R
import com.example.quizkit.data.common.iconCategory
import com.example.quizkit.ui.theme.PrimaryRed
import com.example.quizkit.ui.theme.Red40
import com.example.quizkit.ui.theme.RedGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardQuiz(category: String, quizCategory: String, navigateToQuiz: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, color = RedGrey40),
        onClick = {
            navigateToQuiz(category)
        },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Surface(
                    color = Red40,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = iconCategory[category] ?: R.drawable.box_stype),
                        contentDescription = "logo",
                        modifier = Modifier.padding(10.dp),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = quizCategory,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryRed
                    )
                    Text(
                        text = category,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = RedGrey40
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Quiz-play",
                modifier = Modifier.size(30.dp),
                tint = PrimaryRed
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}
