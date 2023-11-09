package com.cookiegames.smartcookie.core.ui.preference

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Preference(
    key: String? = null,
    title: String,
    summary: String? = null,
    onClick: () -> Unit = {}
) {
    Text(text = title)
}