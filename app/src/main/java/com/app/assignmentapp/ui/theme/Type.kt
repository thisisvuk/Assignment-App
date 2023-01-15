package com.app.assignmentapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.assignmentapp.R


private val Gilmer = FontFamily(
    Font(R.font.gilmer_light, FontWeight.W300),
    Font(R.font.gilmer_regular, FontWeight.W400),
    Font(R.font.gilmer_medium, FontWeight.W500),
    Font(R.font.gilmer_bold, FontWeight.W600)
)

// Set of Material typography styles to start with
data class Typo(
    val displayLarge: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
        lineHeight = 38.sp
    ),
    val displayMedium: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    val displaySmall: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    val headlineLarge: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    val headlineMedium: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    val headlineSmall: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp,
        lineHeight = 17.sp
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = Gilmer,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

val LocalTypography = compositionLocalOf { Typo() }

val MaterialTheme.type: Typo
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current