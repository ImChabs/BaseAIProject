package com.example.baseaiproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseaiproject.ui.theme.BaseAiProjectTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    entryReason: UnauthenticatedEntryReason = UnauthenticatedEntryReason.Default,
    onContinue: () -> Unit = {}
) {
    val description = when (entryReason) {
        UnauthenticatedEntryReason.Default ->
            "Authentication entry will be added in a later block."
        UnauthenticatedEntryReason.StartupConnectivityIssue ->
            "Startup session restoration could not finish because connectivity was unavailable."
        UnauthenticatedEntryReason.ForcedLogout ->
            "The previous session ended and requires a new sign-in."
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Login screen",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = onContinue) {
                Text(text = "Continue to home")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    BaseAiProjectTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenForcedLogoutPreview() {
    BaseAiProjectTheme {
        LoginScreen(entryReason = UnauthenticatedEntryReason.ForcedLogout)
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenStartupConnectivityIssuePreview() {
    BaseAiProjectTheme {
        LoginScreen(entryReason = UnauthenticatedEntryReason.StartupConnectivityIssue)
    }
}
