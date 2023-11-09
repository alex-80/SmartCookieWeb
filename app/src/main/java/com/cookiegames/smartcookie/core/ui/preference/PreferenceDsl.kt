package com.cookiegames.smartcookie.core.ui.preference

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

interface PreferenceScope {
    fun preference(
        content: @Composable PreferenceScope.() -> Unit
    )

    fun preferenceCategory(
        title: String,
        content: @Composable PreferenceScope.() -> Unit
    )

}

internal class PreferenceScopeImpl : PreferenceScope {
    private var _headerIndexes: MutableList<Int> = mutableListOf()
    var headerIndexes: List<Int> = _headerIndexes

    private var _footerIndexes: MutableList<Int> = mutableListOf()
    var footerIndexes: List<Int> = _footerIndexes

    private var _prefsItems: MutableList<PreferenceItem> = mutableListOf()
    var prefsItems: List<PreferenceItem> = _prefsItems

    override fun preference(content: @Composable PreferenceScope.() -> Unit) {
        _prefsItems.add(PreferenceItem(content = { @Composable { content() } }))
    }

    override fun preferenceCategory(
        title: String,
        items: @Composable PreferenceScope.() -> Unit
    ) {
        _headerIndexes.add(_prefsItems.size)

        preference{
            GroupHeader(title = title)
        }

//        apply(items)

        preference {
            Spacer(modifier = Modifier.height(16.dp))
        }

        _footerIndexes.add(_prefsItems.size - 2)
        _headerIndexes.add(_prefsItems.size - 1)
    }
}

internal class PreferenceItem(
    val content: PreferenceScope.(index: Int) -> @Composable () -> Unit,
)