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
fun SensitiveScreen(
    modifier: Modifier = Modifier,
    sessionLifecycle: ProtectedSessionLifecycle = ProtectedSessionLifecycle.Active,
    onLogout: () -> Unit = {},
    onOpenHome: () -> Unit = {},
    onOpenSessionSecurity: () -> Unit = {}
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
                text = "Sensitive screen",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Protected sensitive content will be added in a later block.",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = sessionLifecycle.title,
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = onLogout) {
                Text(text = "Log out")
            }
            Button(onClick = onOpenHome) {
                Text(text = "Back to home")
            }
            Button(onClick = onOpenSessionSecurity) {
                Text(text = "Open session security")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SensitiveScreenPreview() {
    BaseAiProjectTheme {
        SensitiveScreen()
    }
}
