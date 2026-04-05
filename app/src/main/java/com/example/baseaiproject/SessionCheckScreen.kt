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
fun SessionCheckScreen(
    modifier: Modifier = Modifier,
    onAction: (SessionCheckAction) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Checking session",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Choose a placeholder startup outcome while real session restoration is still local-only.",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = { onAction(SessionCheckAction.ContinueUnauthenticated) }) {
                Text(text = "No session found")
            }
            Button(onClick = { onAction(SessionCheckAction.RestoreAuthenticatedSession) }) {
                Text(text = "Restore active session")
            }
            Button(onClick = { onAction(SessionCheckAction.RestoreExpiredSession) }) {
                Text(text = "Restore expired session")
            }
            Button(onClick = { onAction(SessionCheckAction.ReportConnectivityIssue) }) {
                Text(text = "No internet at startup")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SessionCheckScreenPreview() {
    BaseAiProjectTheme {
        SessionCheckScreen()
    }
}
