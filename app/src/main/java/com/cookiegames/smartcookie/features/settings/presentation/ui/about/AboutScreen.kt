package com.cookiegames.smartcookie.features.settings.presentation.ui.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.core.ui.prefs.GroupHeader
import com.cookiegames.smartcookie.core.ui.prefs.PrefsScreen
import com.cookiegames.smartcookie.core.ui.prefs.items.TextPref

@Composable
fun AboutScreen(viewModel: AboutScreenViewModel = hiltViewModel()) {

  PrefsScreen(
    modifier = Modifier,
    dataSource = viewModel.dataStore,
  ) {
    prefsGroup({ GroupHeader(title = stringResource(id = R.string.settings_about)) }) {
      prefsItem {
        TextPref(
          title = stringResource(R.string.more),
          summary = stringResource(R.string.url_project),
          onClick = { viewModel.toHomePage() },
        )
      }

      prefsItem {
        TextPref(
          title = stringResource(R.string.version),
          summary = viewModel.versionName
        )
      }

      prefsItem {
        TextPref(
          title = stringResource(R.string.webview_version),
          summary = viewModel.webViewVersion
        )
      }
    }

    prefsGroup({ GroupHeader(title = stringResource(id = R.string.licenses)) }) {
      prefsItem {
        TextPref(
          title = stringResource(R.string.app_name),
          summary = stringResource(id = R.string.mpl_license),
          onClick = {
            viewModel.openLinkByUrl("http://www.mozilla.org/MPL/2.0/")
          },
        )
      }
      prefsItem {
        TextPref(
          title = stringResource(R.string.app_lightning),
          summary = stringResource(id = R.string.mpl_license),
          onClick = {
            viewModel.openLinkByUrl("http://www.mozilla.org/MPL/2.0/")
          },
        )
      }
      prefsItem {
        TextPref(
          title = stringResource(R.string.android_open_source_project),
          summary = stringResource(id = R.string.apache),
          onClick = {
            viewModel.openLinkByUrl("http://www.apache.org/licenses/LICENSE-2.0")
          },
        )
      }
      prefsItem {
        TextPref(
          title = stringResource(R.string.adguard_ad_server_list),
          summary = stringResource(id = R.string.license_gnu_gpl),
          onClick = {
            viewModel.openLinkByUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
          },
        )
      }
      prefsItem {
        TextPref(
          title = stringResource(R.string.library_netcipher),
          summary = stringResource(id = R.string.license_gnu),
          onClick = {
            viewModel.openLinkByUrl("http://www.gnu.org/licenses/lgpl.html")
          },
        )
      }
      prefsItem {
        TextPref(
          title = stringResource(R.string.snacktory),
          summary = stringResource(id = R.string.apache),
          onClick = {
            viewModel.openLinkByUrl("http://www.apache.org/licenses/LICENSE-2.0")
          },
        )
      }
      prefsItem {
        TextPref(
          title = stringResource(R.string.jsoup),
          summary = stringResource(id = R.string.mit_license),
          onClick = {
            viewModel.openLinkByUrl("http://jsoup.org/license")
          },
        )
      }
    }
  }
}