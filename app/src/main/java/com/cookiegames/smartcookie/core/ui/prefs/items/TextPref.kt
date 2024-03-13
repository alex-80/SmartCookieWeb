package com.cookiegames.smartcookie.core.ui.prefs.items

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cookiegames.smartcookie.core.ui.prefs.PrefsListItem

@Composable
fun TextPref(
  title: String,
  modifier: Modifier = Modifier,
  summary: String? = null,
  darkenOnDisable: Boolean = false,
  minimalHeight: Boolean = false,
  onClick: () -> Unit = {},
  textColor: Color = MaterialTheme.colorScheme.onPrimary,
  enabled: Boolean = true,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingContent: @Composable (() -> Unit)? = null
) {

  PrefsListItem(
    modifier = if (enabled) modifier.clickable { onClick() } else modifier,
    text = { Text(text = title) },
    enabled = enabled,
    darkenOnDisable = darkenOnDisable,
    textColor = textColor,
    minimalHeight = minimalHeight,
    icon = leadingIcon,
    secondaryText = if (summary != null) ({ Text(text = summary) }) else null,
    trailing = trailingContent
  )

}