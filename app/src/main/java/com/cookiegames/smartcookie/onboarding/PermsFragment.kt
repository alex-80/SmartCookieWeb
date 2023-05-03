/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Created by CookieJarApps 10/01/2020 */

package com.cookiegames.smartcookie.onboarding

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.cookiegames.smartcookie.AppTheme
import com.cookiegames.smartcookie.di.injector
import com.cookiegames.smartcookie.preference.UserPreferences
import com.cookiegames.smartcookie.ui.onboarding.PermissionsScreen
import javax.inject.Inject


class PermsFragment : Fragment() {

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        var col: Color
        var textCol: Color

        when (userPreferences.useTheme) {
            AppTheme.LIGHT ->{
                col = Color.White
                textCol = Color.Black
            }
            AppTheme.DARK ->{
                textCol = Color.White
                col = Color.Black
            }
            AppTheme.BLACK ->{
                textCol = Color.White
                col = Color.Black
            }
        }
        setContent {
            PermissionsScreen(
                modifier = Modifier.background(col),
                textColor = textCol,
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    companion object {
        fun newInstance() : PermsFragment {
            return PermsFragment()
        }
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}
