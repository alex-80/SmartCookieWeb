package com.cookiegames.smartcookie.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@OptIn(ExperimentalUnitApi::class)
val AppIntroDefaultHeading =  TextStyle(
    fontSize = TextUnit(
        28f,
        TextUnitType.Sp
    ),
    fontWeight = FontWeight.Bold
)