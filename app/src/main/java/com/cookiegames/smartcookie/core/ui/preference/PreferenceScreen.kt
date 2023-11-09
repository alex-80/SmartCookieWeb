package com.cookiegames.smartcookie.core.ui.preference

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun PreferenceScreen(
    content: @Composable () -> Unit
) {
    Column {
        content()
    }
}