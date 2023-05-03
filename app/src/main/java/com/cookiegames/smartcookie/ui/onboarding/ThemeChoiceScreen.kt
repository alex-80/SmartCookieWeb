package com.cookiegames.smartcookie.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.ui.theme.AppIntroDefaultHeading

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ThemeChoiceScreen(
    modifier: Modifier = Modifier,
    themes: List<String> = emptyList(),
    selectedTheme: String? = null,
    onThemeSelected: (String, Int) -> Unit = { _: String, _: Int -> },
    textColor: Color = Color.Black
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .then(modifier)
            .padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.theme),
            style = AppIntroDefaultHeading.copy(
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.appintro_headtext_size).value,
                    TextUnitType.Sp
                ),
                color = textColor,
            )
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 48.dp, top = 16.dp)
        ) {
            items(themes.size) { index ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = themes[index], color = textColor)
                    RadioButton(
                        selected = selectedTheme == themes[index],
                        onClick = { onThemeSelected(themes[index], index) },
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun ThemeChoiceScreenPreview() {
    ThemeChoiceScreen(
        themes = listOf("Light", "Dark"),
        selectedTheme = "Light",
    )
}