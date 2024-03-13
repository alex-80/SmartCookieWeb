package com.cookiegames.smartcookie.features.settings.presentation.ui.about

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.core.usecase.GetVersionName
import com.cookiegames.smartcookie.core.usecase.GetWebViewVersionName
import com.cookiegames.smartcookie.core.usecase.OpenLink
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
  @ApplicationContext private val context: Context,
  private val openLink: OpenLink,
  private val getVersionName: GetVersionName,
  private val getWebViewVersion: GetWebViewVersionName,
  val dataStore: DataStore<Preferences>
) : ViewModel() {

  val versionName: String
    get() = getVersionName()

  val webViewVersion: String
    get() = getWebViewVersion()

  private fun openLinkById(id: Int) {
    openLink(context.getString(id))
  }

  fun openLinkByUrl(url: String) {
    openLink(url)
  }

  fun toHomePage() {
    // get string by id
    openLinkById((R.string.url_project))
  }
}