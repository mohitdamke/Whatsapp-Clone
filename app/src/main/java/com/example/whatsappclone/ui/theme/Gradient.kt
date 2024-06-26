package com.example.whatsappclone.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Gradient() {

    val horizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF8BB4FF),
            Color(0xFF21CBFF),
            Color(0xFF73FFF7)
        )
    )


    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF8BB4FF),
            Color(0xFF21CBFF),
            Color(0xFF73FFF7)
        )
    )
}