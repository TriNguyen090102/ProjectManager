package com.example.projectmanager.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.projectmanager.R

private val raleway = FontFamily(
    Font(R.font.raleway_regular),
    Font(R.font.raleway_medium, FontWeight.W500),
    Font(R.font.raleway_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
// Set of Material typography styles to start with
val typography = Typography(
    h2 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp
    ),
    h3 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    h4 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = raleway,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp

    )
)