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
import com.cookiegames.smartcookie.di.injector
import com.cookiegames.smartcookie.preference.UserPreferences
import com.cookiegames.smartcookie.search.SearchEngineProvider
import com.cookiegames.smartcookie.search.engine.BaseSearchEngine
import com.cookiegames.smartcookie.features.onboarding.SearchChoiceScreen
import javax.inject.Inject


class SearchEngineFragment : Fragment() {
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
        val values = convertSearchEngineToString(searchEngineProvider.provideAllSearchEngines())
        val selectedEngine = mutableStateOf(values[1])
        setContent {
            SearchChoiceScreen(
                modifier = Modifier.background(col),
                engines = values.drop(1).toTypedArray(),
                selectedEngine = selectedEngine.value,
                onEngineSelected = { engine, index ->
                    selectedEngine.value = engine
                    userPreferences.searchChoice =
                        searchEngineProvider.mapSearchEngineToPreferenceIndex(searchEngineProvider.provideAllSearchEngines()[index + 1])
                },
                textColor = textCol
            )
        }
    }

    private fun convertSearchEngineToString(searchEngines: List<BaseSearchEngine>): Array<String> =
        searchEngines.map { getString(it.titleRes) }.toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)
    }

    companion object {
        fun newInstance(): SearchEngineFragment {
            return SearchEngineFragment()
        }
    }
}
