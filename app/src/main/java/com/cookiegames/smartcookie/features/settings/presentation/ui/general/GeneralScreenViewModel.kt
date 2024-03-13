package com.cookiegames.smartcookie.features.settings.presentation.ui.general

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.cookiegames.smartcookie.core.usecase.OpenLink
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class GeneralScreenViewModel @Inject constructor(
  @ApplicationContext private val context: Context,
  private val openLink: OpenLink,
  val dataStore: DataStore<Preferences>
) : ViewModel() {

  private fun openLinkById(id: Int) {
    openLink(context.getString(id))
  }

  fun openLinkByUrl(url: String) {
    openLink(url)
  }

}