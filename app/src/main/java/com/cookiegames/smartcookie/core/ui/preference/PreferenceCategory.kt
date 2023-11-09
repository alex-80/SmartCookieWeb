package com.cookiegames.smartcookie.core.ui.preference

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PreferenceCategory(
    title: String,
    content: @Composable () -> Unit
) {
    Text(text = title)
    content()
}
