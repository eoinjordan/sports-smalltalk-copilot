package com.example.sportssmalltalk.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TerminalGreen = Color(0xFF80D93F)
val TerminalAmber = Color(0xFFFF9800)
val InkBlack = Color(0xFF050706)
val PanelBlack = Color(0xFF10140F)
val PanelSoft = Color(0xFF182015)
val Paper = Color(0xFFF3DFAE)
val PaperInk = Color(0xFF20170D)
val MutedText = Color(0xFFC7D3B8)

private val AppColors = darkColorScheme(
    primary = TerminalGreen,
    onPrimary = Color.Black,
    secondary = TerminalAmber,
    onSecondary = Color.Black,
    background = InkBlack,
    onBackground = Color(0xFFF4F7EC),
    surface = PanelBlack,
    onSurface = Color(0xFFF4F7EC),
    surfaceVariant = PanelSoft,
    onSurfaceVariant = Color(0xFFD8E7C7),
    error = Color(0xFFFF6B6B)
)

private val RetroTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 36.sp,
        letterSpacing = 1.4.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 29.sp,
        letterSpacing = 1.2.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        letterSpacing = 0.4.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 12.sp,
        lineHeight = 17.sp
    )
)

@Composable
fun SportsSmallTalkTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = RetroTypography,
        content = content
    )
}
