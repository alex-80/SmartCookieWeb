package com.cookiegames.smartcookie.core.ui.prefs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

interface PrefsScope {
  fun prefsItem(
    content: @Composable PrefsScope.() -> Unit
  )

  fun prefsGroup(
    title: String,
    items: PrefsScope.() -> Unit
  )

  fun prefsGroup(header: @Composable PrefsScope.() -> Unit, items: PrefsScope.() -> Unit)

}

internal class PrefsScopeImpl : PrefsScope {
  private var _headerIndexes: MutableList<Int> = mutableListOf()
  val headerIndexes: List<Int> get() = _headerIndexes

  private var _footerIndexes: MutableList<Int> = mutableListOf()
  val footerIndexes: List<Int> get() = _footerIndexes

  private var _prefsItems: MutableList<PrefsItem> = mutableListOf()
  val prefsItems: List<PrefsItem> get() = _prefsItems

  override fun prefsItem(content: @Composable PrefsScope.() -> Unit) {
    _prefsItems.add(
      PrefsItem(
        content = { @Composable { content() } }
      )
    )
  }

  override fun prefsGroup(
    header: @Composable (PrefsScope.() -> Unit),
    items: PrefsScope.() -> Unit
  ) {
    _headerIndexes.add(this.prefsItems.size)

    this.prefsItem { Spacer(modifier = Modifier.height(16.dp)) }

    this.prefsItem {
      header();
    }

    this.apply(items)

    this.prefsItem { Spacer(modifier = Modifier.height(8.dp)) }

    _footerIndexes.add(this.prefsItems.size - 2)
    _footerIndexes.add(this.prefsItems.size - 1)
  }

  override fun prefsGroup(title: String, items: PrefsScope.() -> Unit) {
    this.prefsGroup({
      GroupHeader(title = title)
    }, items)
  }

  fun getPrefsItem(index: Int): @Composable () -> Unit {
    val prefsItem = prefsItems[index]
    return prefsItem.content.invoke(this, index)
  }

}

internal class PrefsItem(
  val content: PrefsScope.(index: Int) -> @Composable () -> Unit,
)