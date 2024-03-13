package com.cookiegames.smartcookie.features.settings.presentation.ui.general

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.core.ui.prefs.GroupHeader
import com.cookiegames.smartcookie.core.ui.prefs.PrefsScreen
import com.cookiegames.smartcookie.core.ui.prefs.items.SwitchPref

@Composable
fun GeneralScreen(viewModel: GeneralScreenViewModel = hiltViewModel()) {
  PrefsScreen(dataSource = viewModel.dataStore) {
    prefsGroup({
      GroupHeader(title = stringResource(id = R.string.settings_general))
    }) {
      prefsItem {
        SwitchPref(
          key = "cb_images",
          title = stringResource(id = R.string.block)
        )
      }
    }
  }
}