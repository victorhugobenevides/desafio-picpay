package com.picpay.desafio.android.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val darkColorScheme = darkColorScheme(
    primary = colorPrimary,
    onPrimary = Color.White,
    primaryContainer = colorPrimaryLight,
    onPrimaryContainer = colorPrimaryDark,
    secondary = colorAccent,
    onSecondary = Color.Black,
    background = colorPrimaryDark,
    onBackground = Color.White,
    surface = colorPrimary,
    onSurface = Color.White
)

private val lightColorScheme = lightColorScheme(
    primary = colorPrimary,
    onPrimary = Color.White,
    primaryContainer = colorPrimaryLight,
    onPrimaryContainer = colorPrimaryDark,
    secondary = colorAccent,
    onSecondary = Color.Black,
    background = colorPrimary,
    onBackground = Color.White,
    surface = colorPrimaryLight,
    onSurface = colorPrimaryDark
)

// Configuração de tipografia padrão
val typography = Typography(
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorDetail
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = colorPrimaryLight
    )
)

@Composable
fun PicPayTheme(
    darkTheme: Boolean = true, // Define tema escuro como padrão
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
