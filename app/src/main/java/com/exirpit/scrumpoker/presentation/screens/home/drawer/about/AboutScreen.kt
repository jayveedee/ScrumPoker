package com.exirpit.scrumpoker.presentation.screens.home.drawer.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.exirpit.scrumpoker.R
import com.exirpit.scrumpoker.presentation.common.composable.SPTopAppBarDefault
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme

@Composable
fun AboutScreen(
    viewModel: AboutScreenViewModel = hiltViewModel(),
    onNavigateBackClicked: () -> Unit
) {
    val context = LocalContext.current

    ScrumPokerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                SPTopAppBarDefault(
                    title = "About", //TODO localize
                    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    onNavigationClick = {
                        onNavigateBackClicked.invoke()
                    }
                )
                Image(
                    painter = painterResource(R.drawable.ic_launcher),
                    alignment = Alignment.Center,
                    contentDescription = "Application icon logo", //TODO localize
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(32.dp))
                LabeledTextButton(
                    labelText = "Version", //TODO localize
                    bodyText = viewModel.versionTextStateFlow.collectAsState().value
                )
                LabeledTextButton(
                    labelText = "Updated", //TODO localize
                    bodyText = viewModel.lastUpdateTextStateFlow.collectAsState().value
                )
                NormalTextButton(
                    text = "Open source license", //TODO localize
                    onClick = {
                        viewModel.onOpenSourceLicenseTextClicked(context)
                    }
                )
                NormalTextButton(
                    text = "Privacy policy", //TODO localize
                    onClick = {
                        viewModel.onPrivacyPolicyTextClicked(context)
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LinkIconButton(
                        iconResId = R.drawable.ic_website,
                        contentDescription = "Website icon", //TODO localize
                        onClick = { viewModel.onWebsiteIconClicked(context) }
                    )
                    LinkIconButton(
                        iconResId = R.drawable.ic_github,
                        contentDescription = "Github icon", //TODO localize
                        onClick = { viewModel.onGithubIconClicked(context) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LabeledTextButton(
    modifier: Modifier = Modifier,
    labelText: String,
    bodyText: String,
    onClick: (() -> Unit?)? = null
) {
    TextButton(
        modifier = modifier.fillMaxWidth().padding(0.dp),
        onClick = {
            onClick?.invoke()
        }
    ) {
        LabeledText(
            modifier = modifier.fillMaxWidth(),
            labelText = labelText,
            bodyText = bodyText
        )
    }
}

@Composable
private fun LabeledText(
    modifier: Modifier = Modifier,
    labelText: String,
    bodyText: String
) {
    Column {
        Text(
            modifier = modifier,
            text = labelText,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            modifier = modifier,
            text = bodyText,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun NormalTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier.fillMaxWidth().padding(0.dp),
        onClick = {
            onClick.invoke()
        }
    ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        )
    }
}

@Composable
private fun LinkIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = {
            onClick.invoke()
        }
    ) {
        Icon(
            painter = painterResource(iconResId),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = contentDescription
        )
    }
}