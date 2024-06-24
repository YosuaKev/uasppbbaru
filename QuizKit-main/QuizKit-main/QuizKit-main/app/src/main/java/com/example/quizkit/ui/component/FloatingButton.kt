package com.example.quizkit.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quizkit.R
import com.example.quizkit.ui.theme.PrimaryRed
import com.example.quizkit.ui.theme.Red40

@Composable
fun FloatingButton(navigateToCategory: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .offset(y = 60.dp)
            .size(80.dp)
            .border(
                width = 4.dp,
                color = Red40,
                shape = CircleShape
            ),
        shape = CircleShape,
        onClick = {
            navigateToCategory()
        },
        containerColor = PrimaryRed,
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(id = R.drawable.rocket),
            contentDescription = "Play",
            modifier = Modifier.size(40.dp)
        )
    }
}
