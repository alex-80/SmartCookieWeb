/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Created by CookieJarApps 10/01/2020 */

package com.cookiegames.smartcookie.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.cookiegames.smartcookie.AppTheme
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.di.injector
import com.cookiegames.smartcookie.preference.UserPreferences
import com.cookiegames.smartcookie.search.SearchEngineProvider
import com.cookiegames.smartcookie.ui.onboarding.ThemeChoiceScreen
import javax.inject.Inject


class ThemeChoiceFragment : Fragment() {
    @Inject
    lateinit var searchEngineProvider: SearchEngineProvider

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val col: Color
        val textCol: Color

        when (userPreferences.useTheme) {
            AppTheme.LIGHT -> {
                col = Color.White
                textCol = Color.Black
            }

            AppTheme.DARK -> {
                textCol = Color.White
                col = Color.Black
            }

            AppTheme.BLACK -> {
                textCol = Color.White
                col = Color.Black
            }
        }
        val values = AppTheme.values().map { it }
        val names = AppTheme.values().map { it.toDisplayString() }
        val selectedTheme = mutableStateOf(names[0])
        setContent {
            ThemeChoiceScreen(
                modifier = Modifier.background(col),
                textColor = textCol,
                themes = names,
                selectedTheme = selectedTheme.value,
                onThemeSelected = { theme, index ->
                    selectedTheme.value = theme
                    userPreferences.useTheme = values[index]
//                    activity?.recreate()
                })
        }
    }

    private fun AppTheme.toDisplayString(): String = getString(
        when (this) {
            AppTheme.LIGHT -> R.string.light_theme
            AppTheme.DARK -> R.string.dark_theme
            AppTheme.BLACK -> R.string.black_theme
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
    }

    companion object {
        fun newInstance(): ThemeChoiceFragment {
            return ThemeChoiceFragment()
        }
    }
}
