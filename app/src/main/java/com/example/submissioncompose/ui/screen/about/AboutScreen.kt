package com.example.submissioncompose.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.submissioncompose.R
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import com.example.submissioncompose.ui.theme.blackV
import com.example.submissioncompose.ui.theme.redV
import com.example.submissioncompose.ui.theme.tungstenFont

@Composable
fun AboutScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(blackV)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .border(2.dp, redV, CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ramaadjiprsty@gmail.com",
                fontFamily = tungstenFont,
                color = Color.White,
                letterSpacing = 1.sp,
                fontSize = 18.sp
            )
            Text(
                text = "Rama Adji Prasetyo",
                fontFamily = tungstenFont,
                color = Color.White,
                letterSpacing = 1.sp,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
private fun About() {
    SubmissionComposeTheme {
        AboutScreen()
    }
}
