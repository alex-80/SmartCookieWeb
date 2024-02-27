package com.cookiegames.smartcookie.features.settings.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cookiegames.smartcookie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(viewModel: AboutScreenViewModel = hiltViewModel()) {
  val listItemColors = ListItemDefaults.colors(
    containerColor = Color.Transparent,
    headlineColor = MaterialTheme.colorScheme.onPrimary,
    supportingColor = MaterialTheme.colorScheme.onPrimary
  )

  Column(
    modifier = Modifier
      .fillMaxHeight()
      .verticalScroll(rememberScrollState())
  ) {
    Text(
      modifier = Modifier.padding(start = 14.dp, top = 20.dp),
      text = stringResource(id = R.string.settings_about),
      color = MaterialTheme.colorScheme.primary
    )

    ListItem(
      modifier = Modifier.clickable { viewModel.toHomePage() },
      headlineText = {
        Text(text = stringResource(R.string.more))
      },
      supportingText = {
        Text(text = stringResource(R.string.url_project))
      },
      colors = listItemColors
    )
    ListItem(
      headlineText = {
        Text(text = stringResource(R.string.version))
      },
      colors = listItemColors
    )
    ListItem(
      headlineText = {
        Text(text = stringResource(R.string.webview_version))
      },
      colors = listItemColors
    )
    Divider(
      color = MaterialTheme.colorScheme.onBackground
    )
    Text(
      modifier = Modifier.padding(start = 14.dp, top = 20.dp),
      text = stringResource(id = R.string.licenses),
      color = MaterialTheme.colorScheme.primary
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("http://www.mozilla.org/MPL/2.0/")
      },
      headlineText = {
        Text(text = stringResource(R.string.app_name))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.mpl_license))
      },
      colors = listItemColors
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("http://www.mozilla.org/MPL/2.0/")
      },
      headlineText = {
        Text(text = stringResource(R.string.app_lightning))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.mpl_license))
      },
      colors = listItemColors
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("http://www.apache.org/licenses/LICENSE-2.0")
      },
      headlineText = {
        Text(text = stringResource(R.string.android_open_source_project))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.apache))
      },
      colors = listItemColors
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
      },
      headlineText = {
        Text(text = stringResource(R.string.adguard_ad_server_list))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.license_gnu_gpl))
      },
      colors = listItemColors
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("http://www.gnu.org/licenses/lgpl.html")
      },
      headlineText = {
        Text(text = stringResource(R.string.library_netcipher))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.license_gnu))
      },
      colors = listItemColors
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("http://www.apache.org/licenses/LICENSE-2.0")
      },
      headlineText = {
        Text(text = stringResource(R.string.snacktory))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.apache))
      },
      colors = listItemColors
    )
    ListItem(
      modifier = Modifier.clickable {
        viewModel.openLinkByUrl("http://jsoup.org/license")
      },
      headlineText = {
        Text(text = stringResource(R.string.jsoup))
      },
      supportingText = {
        Text(text = stringResource(id = R.string.mit_license))
      },
      colors = listItemColors
    )
  }
}