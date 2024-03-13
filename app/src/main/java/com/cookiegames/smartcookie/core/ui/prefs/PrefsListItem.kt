package com.cookiegames.smartcookie.core.ui.prefs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun PrefsListItem(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  darkenOnDisable: Boolean = true,
  icon: @Composable (() -> Unit)? = null,
  secondaryText: @Composable (() -> Unit)? = null,
  trailing: @Composable (() -> Unit)? = null,
  textColor: Color = MaterialTheme.colorScheme.onBackground,
  minimalHeight: Boolean = false,
  text: @Composable () -> Unit
) {
  val typography = MaterialTheme.typography
  val styledText = applyTextStyle(
    textStyle = typography.titleMedium,
    textColor = textColor,
    contentAlpha = when {
      enabled || !darkenOnDisable -> AlphaHigh
      else -> AlphaDisabled
    },
    content = text
  )!!

  val styledSecondaryText = applyTextStyle(
    textStyle = typography.bodyMedium,
    textColor = textColor,
    contentAlpha = when {
      enabled || !darkenOnDisable -> AlphaMedium
      else -> AlphaDisabled
    },
    content = secondaryText
  )

  val styledTrailing = applyTextStyle(
    textStyle = typography.bodySmall,
    textColor = textColor,
    contentAlpha = when {
      enabled || !darkenOnDisable -> AlphaHigh
      else -> AlphaDisabled
    },
    content = trailing
  )

  AnyLine.CustomListItem(
    modifier = modifier,
    minimalHeight = minimalHeight,
    icon = icon,
    secondaryText = styledSecondaryText,
    trailing = styledTrailing,
    text = styledText
  )

}

private object AnyLine {
  private val MinHeight = 48.dp
  private val MinHeightSmaller = 32.dp
  private val IconMinPaddedWidth = 40.dp
  private val ContentPadding = 16.dp
  private val VerticalPadding = 12.dp
  private val SingleLinePadding = 4.dp

  @Composable
  fun CustomListItem(
    modifier: Modifier = Modifier,
    minimalHeight: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    secondaryText: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    text: @Composable () -> Unit
  ) {
    Row(
      modifier = modifier
        .heightIn(min = if (minimalHeight) MinHeightSmaller else MinHeight)
        .padding(
          start = ContentPadding,
          end = ContentPadding,
          top = when {
            secondaryText == null && !minimalHeight -> SingleLinePadding
            else -> VerticalPadding
          },
          bottom = when {
            minimalHeight -> 0.dp
            secondaryText == null -> SingleLinePadding
            else -> VerticalPadding
          }
        )
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      if (icon != null) {
        val minSize = IconMinPaddedWidth
        Box(
          modifier = Modifier.sizeIn(minWidth = minSize, minHeight = minSize),
          contentAlignment = Alignment.CenterStart
        ) {
          icon()
        }
      }

      Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center,
      ) {
        text()
        secondaryText?.invoke()
      }
      trailing?.invoke()
    }
  }
}

private fun applyTextStyle(
  textStyle: TextStyle,
  textColor: Color,
  contentAlpha: Float,
  content: @Composable (() -> Unit)?
): @Composable (() -> Unit)? {
  if (content == null) return null

  return {
    val newTextStyle = textStyle.copy(
      color = textColor.copy(alpha = contentAlpha)
    )
    CompositionLocalProvider(LocalTextStyle provides newTextStyle) {
      content()
    }
  }
}

private const val AlphaHigh = 1.0f
private const val AlphaMedium = 0.74f
private const val AlphaDisabled = 0.38f