package com.cookiegames.smartcookie.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.ui.theme.AppIntroDefaultHeading

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SearchChoiceScreen(
    modifier: Modifier = Modifier,
    engines: Array<String> = emptyArray(),
    selectedEngine: String? = null,
    onEngineSelected: (String, Int) -> Unit = { _: String, _: Int -> },
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
            text = stringResource(id = R.string.search),
            style = AppIntroDefaultHeading.copy(
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.appintro_headtext_size).value,
                    TextUnitType.Sp
                ),
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 48.dp, top = 16.dp)
        ) {
            items(engines.size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = engines[index],
                        style = TextStyle(
                            color = textColor,
                        )
                    )
                    RadioButton(selected = selectedEngine == engines[index], onClick = {
                        onEngineSelected(engines[index], index)
                    })
                }

            }
        }
    }

}

@Preview
@Composable
fun SearchChoiceScreenPreview() {
    SearchChoiceScreen(
        engines = arrayOf("Google", "DuckDuckGo", "Bing", "Yahoo", "Ecosia", "Qwant", "Startpage"),
        selectedEngine = "Google"
    )
}