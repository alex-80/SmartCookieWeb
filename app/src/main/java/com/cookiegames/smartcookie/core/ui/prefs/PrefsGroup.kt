package com.cookiegames.smartcookie.core.ui.prefs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrefsGroup(
  title: String,
  content: @Composable () -> Unit
) {
  Text(
    modifier = Modifier.padding(start = 14.dp, top = 20.dp),
    text = title,
    color = MaterialTheme.colorScheme.primary
  )
  content()
}
