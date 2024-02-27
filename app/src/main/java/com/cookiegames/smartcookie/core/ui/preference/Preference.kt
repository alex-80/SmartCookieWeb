package com.cookiegames.smartcookie.core.ui.preference

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Preference(
    key: String? = null,
    title: String,
    summary: String? = null,
    onClick: () -> Unit = {}
) {
    Text(text = title, color = Color.White)
}