/*
 * Copyright 2014 A.C.R. Development
 */
package com.cookiegames.smartcookie.settings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.cookiegames.smartcookie.features.settings.presentation.ui.AboutScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutSettingsFragment : AbstractSettingsFragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//        var webview = resources.getString(R.string.unknown)
//
//        context?.let {
//            WebViewCompat.getCurrentWebViewPackage(it)?.versionName?.let {
//                webview = it
//           }
//        }
//
//
//        clickablePreference(
//                preference = SETTINGS_VERSION,
//                summary = BuildConfig.VERSION_NAME,
//                onClick = { }
//        )
//
//        clickablePreference(
//                preference = WEBVIEW_VERSION,
//                summary = webview,
//                onClick = { }
//        )
//
//        val aboutPref: androidx.preference.Preference? = findPreference(SETTINGS_VERSION)
//        aboutPref!!.setOnPreferenceClickListener {
//            val builder = MaterialAlertDialogBuilder(requireContext())
//            builder.setTitle("SCW v" + BuildConfig.VERSION_NAME)
//            builder.setMessage("What's new:\n- New settings page")
//
//
//            builder.setPositiveButton(resources.getString(R.string.action_ok)){ dialogInterface, which ->
//
//            }
//            val alertDialog: AlertDialog = builder.create()
//            alertDialog.setCancelable(false)
//            alertDialog.show()
//            true
//        }

  }

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        addPreferencesFromResource(R.xml.preference_about)
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

  companion object {
    private const val SETTINGS_VERSION = "pref_version"
    private const val WEBVIEW_VERSION = "pref_webview"
  }
}
