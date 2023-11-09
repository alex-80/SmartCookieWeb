package com.cookiegames.smartcookie.features.settings.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.core.ui.preference.Preference
import com.cookiegames.smartcookie.core.ui.preference.PreferenceCategory
import com.cookiegames.smartcookie.core.ui.preference.PreferenceScreen

@Composable
fun AboutScreen() {
    PreferenceScreen {
        PreferenceCategory(title = stringResource(R.string.settings_about)) {
            Preference(
                summary = stringResource(R.string.url_project),
                title = stringResource(R.string.more),
                onClick = {
//                    Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("https://github.com/CookieJarApps/SmartCookieWeb")
//                    ).apply {
//                        putExtra("SOURCE", "SELF")
//                    }.let(context.startActivity)
                }
            )
            Preference(
                key = "pref_version",
                title = stringResource(R.string.version)
            )
            Preference(
                key = "pref_webview",
                title = stringResource(R.string.webview_version)
            )
        }
    }
}