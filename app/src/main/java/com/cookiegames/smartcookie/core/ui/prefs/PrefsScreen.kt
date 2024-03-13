package com.cookiegames.smartcookie.core.ui.prefs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

lateinit var LocalPrefsDataStore: ProvidableCompositionLocal<DataStore<Preferences>>

@Composable
fun PrefsScreen(
  modifier: Modifier = Modifier,
  dataSource: DataStore<Preferences>,
  dividerThickness: Dp = 1.dp,
  content: PrefsScope.() -> Unit
) {
  LocalPrefsDataStore = staticCompositionLocalOf { dataSource }
  val prefsScope = PrefsScopeImpl().apply(content);

  CompositionLocalProvider(LocalPrefsDataStore provides dataSource) {
    Column(modifier = modifier) {
      Spacer(modifier = Modifier.height(12.dp))
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(prefsScope.prefsItems.size) { index ->

          if (dividerThickness != 0.dp
            && index != 0
            && prefsScope.headerIndexes.contains(index)
          ) {
            HorizontalDivider(
              thickness = dividerThickness,
              color = MaterialTheme.colorScheme.onBackground
            )
          }

          prefsScope.getPrefsItem(index)()

        }
      }
    }
  }

}