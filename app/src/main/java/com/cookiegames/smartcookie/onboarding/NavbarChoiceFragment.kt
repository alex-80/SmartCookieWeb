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
import com.cookiegames.smartcookie.ui.onboarding.Navbar
import com.cookiegames.smartcookie.ui.onboarding.NavbarChoiceScreen
import javax.inject.Inject


class NavbarChoiceFragment : Fragment() {
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
        val selectedNavbarOne = mutableStateOf(Navbar.DEFAULT)
        val selectedNavbarTwo = mutableStateOf(Navbar.DEFAULT_TABS)

        setContent {
            NavbarChoiceScreen(
                modifier = Modifier.background(col),
                textColor = textCol,
                selectedGroupOne = selectedNavbarOne.value,
                selectedGroupTwo = selectedNavbarTwo.value,
                onNavbarSelected = { nav ->

                    when (nav) {
                        Navbar.DEFAULT -> {
                            userPreferences.bottomBar = false
                            selectedNavbarOne.value = nav
                        }

                        Navbar.BOTH -> {
                            userPreferences.bottomBar = false
                            userPreferences.navbar = true
                            selectedNavbarOne.value = nav
                        }

                        Navbar.BOTTOM -> {
                            userPreferences.bottomBar = true
                            selectedNavbarOne.value = nav
                        }

                        Navbar.DEFAULT_TABS -> {
                            userPreferences.showTabsInDrawer = true
                            selectedNavbarTwo.value = nav
                        }

                        Navbar.FULL_TABS -> {
                            userPreferences.showTabsInDrawer = false
                            selectedNavbarTwo.value = nav
                        }
                    }
                },
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val rGroup = getView()?.findViewById(R.id.radioGroup) as RadioGroup
//        rGroup.setOnCheckedChangeListener { group, checkedId ->
//            when (checkedId) {
//                R.id.defaultNavbar -> userPreferences.bottomBar = false
//                R.id.defaultNavbar2nd -> {
//                    userPreferences.bottomBar = false
//                    userPreferences.navbar = true
//                }
//
//                R.id.bottomNavbar -> userPreferences.bottomBar = true
//            }
//        }
//        val rGroup2 = getView()?.findViewById(R.id.radioGroup2) as RadioGroup
//        rGroup2.setOnCheckedChangeListener { group, checkedId ->
//            when (checkedId) {
//                R.id.defaultTabs -> userPreferences.showTabsInDrawer = true
//                R.id.fullTabs -> userPreferences.showTabsInDrawer = false
//            }
//        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    companion object {
        fun newInstance(): NavbarChoiceFragment {
            return NavbarChoiceFragment()
        }
    }
}
