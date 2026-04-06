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
fun SessionSecurityScreen(
    modifier: Modifier = Modifier,
    sessionLifecycle: ProtectedSessionLifecycle = ProtectedSessionLifecycle.Active,
    onAction: (SessionSecurityAction) -> Unit = {},
    onLogout: () -> Unit = {},
    onOpenHome: () -> Unit = {},
    onOpenSensitive: () -> Unit = {}
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
                text = "Session security screen",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Session visibility and controls will be added in a later block.",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = sessionLifecycle.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = sessionLifecycle.description,
                style = MaterialTheme.typography.bodyMedium
            )
            when (sessionLifecycle) {
                ProtectedSessionLifecycle.Active -> {
                    Button(onClick = { onAction(SessionSecurityAction.MarkAccessExpired) }) {
                        Text(text = "Expire access")
                    }
                }

                ProtectedSessionLifecycle.AccessExpired -> {
                    Button(onClick = { onAction(SessionSecurityAction.StartRefresh) }) {
                        Text(text = "Start refresh attempt")
                    }
                    Button(onClick = { onAction(SessionSecurityAction.MarkSessionActive) }) {
                        Text(text = "Reset to active")
                    }
                }

                ProtectedSessionLifecycle.Refreshing -> {
                    Button(onClick = { onAction(SessionSecurityAction.CompleteRefresh) }) {
                        Text(text = "Refresh succeeded")
                    }
                    Button(onClick = { onAction(SessionSecurityAction.BlockRefreshForConnectivity) }) {
                        Text(text = "Connectivity blocked refresh")
                    }
                    Button(onClick = { onAction(SessionSecurityAction.FailRefresh) }) {
                        Text(text = "Refresh failed and sign out")
                    }
                }

                ProtectedSessionLifecycle.RefreshBlockedByConnectivity -> {
                    Button(onClick = { onAction(SessionSecurityAction.StartRefresh) }) {
                        Text(text = "Retry refresh attempt")
                    }
                    Button(onClick = { onAction(SessionSecurityAction.MarkAccessExpired) }) {
                        Text(text = "Return to expired access")
                    }
                }

                ProtectedSessionLifecycle.Unrecoverable -> {
                    Button(onClick = { onAction(SessionSecurityAction.MarkSessionActive) }) {
                        Text(text = "Reset to active")
                    }
                }
            }
            Button(onClick = onLogout) {
                Text(text = "Log out")
            }
            Button(onClick = onOpenHome) {
                Text(text = "Back to home")
            }
            Button(onClick = onOpenSensitive) {
                Text(text = "Open sensitive screen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SessionSecurityScreenPreview() {
    BaseAiProjectTheme {
        SessionSecurityScreen(
            sessionLifecycle = ProtectedSessionLifecycle.RefreshBlockedByConnectivity
        )
    }
}
