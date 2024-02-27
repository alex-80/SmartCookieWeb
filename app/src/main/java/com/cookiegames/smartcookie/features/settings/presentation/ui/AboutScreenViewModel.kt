package com.cookiegames.smartcookie.features.settings.presentation.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.core.usecase.OpenLink
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
  @ApplicationContext private val context: Context,
  private val openLink: OpenLink
) : ViewModel() {

  fun openLinkById(id: Int) {
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