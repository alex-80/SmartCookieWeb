package com.cookiegames.smartcookie.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
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
fun PermissionsScreen(modifier: Modifier = Modifier, textColor: Color = Color.Black) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .then(modifier)
            .padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.grant_permissions),
            style = AppIntroDefaultHeading.copy(
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.appintro_headtext_size).value,
                    TextUnitType.Sp
                ),
                color = textColor,
            )
        )
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.perms),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(bottom = 128.dp),
            text = stringResource(id = R.string.grant_permissions_desc),
            style = TextStyle(
                color = textColor,
                fontSize = TextUnit(
                    dimensionResource(id = R.dimen.appintro_desctext_size).value,
                    TextUnitType.Sp
                ),
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Preview
@Composable
fun PermissionsScreenPreview() {
    PermissionsScreen()
}