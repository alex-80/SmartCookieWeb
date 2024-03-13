/*
 * Copyright 2014 A.C.R. Development
 */
package com.cookiegames.smartcookie.settings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.cookiegames.smartcookie.features.settings.presentation.ui.about.AboutScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutSettingsFragment : AbstractSettingsFragment() {

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = ComposeView(requireContext()).apply {
    setContent {
      AboutScreen()
    }
  }

}
