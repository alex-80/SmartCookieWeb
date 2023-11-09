package com.cookiegames.smartcookie.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.core.ui.theme.AppIntroDefaultHeading

enum class Navbar {
    DEFAULT,
    BOTH,
    BOTTOM,
    DEFAULT_TABS,
    FULL_TABS
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun NavbarChoiceScreen(
    modifier: Modifier = Modifier,
    selectedGroupOne: Navbar = Navbar.DEFAULT,
    selectedGroupTwo: Navbar = Navbar.DEFAULT_TABS,
    onNavbarSelected: (Navbar) -> Unit = {},
    textColor: Color = Color.Black
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .then(modifier)
    ) {
        Text(
            modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp),
            text = stringResource(id = R.string.navbar),
            style = AppIntroDefaultHeading.copy(
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.appintro_headtext_size).value,
                    TextUnitType.Sp
                ),
                color = textColor,
            )
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(bottom = 8.dp, top = 16.dp),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.top_hdpi),
                        contentDescription = ""
                    )
                    RadioButton(selected = selectedGroupOne == Navbar.DEFAULT, onClick = {
                        onNavbarSelected(Navbar.DEFAULT)
                    })
                }
            }
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.both_hdpi),
                        contentDescription = ""
                    )
                    RadioButton(selected = selectedGroupOne == Navbar.BOTH, onClick = {
                        onNavbarSelected(
                            Navbar.BOTH,
                        )
                    })
                }
            }
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.bottom_hdpi),
                        contentDescription = ""
                    )
                    RadioButton(selected = selectedGroupOne == Navbar.BOTTOM, onClick = {
                        onNavbarSelected(Navbar.BOTTOM)
                    })
                }
            }
        }
        Divider(modifier = Modifier.padding(horizontal = 32.dp))
        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 48.dp, top = 32.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.top_hdpi),
                        contentDescription = ""
                    )
                    RadioButton(selected = selectedGroupTwo == Navbar.DEFAULT_TABS, onClick = {
                        onNavbarSelected(Navbar.DEFAULT_TABS)
                    })
                }
            }
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.top_tabs),
                        contentDescription = ""
                    )
                    RadioButton(selected = selectedGroupTwo == Navbar.FULL_TABS, onClick = {
                        onNavbarSelected(Navbar.FULL_TABS)
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun NavbarChoiceScreenPreview() {
    NavbarChoiceScreen()
}