package com.example.baseaiproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseaiproject.ui.theme.BaseAiProjectTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    sessionLifecycle: ProtectedSessionLifecycle = ProtectedSessionLifecycle.Active,
    onLogout: () -> Unit = {},
    onOpenSensitive: () -> Unit = {},
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
            Text(text = formatGreeting("Android"))
            Text(text = sessionLifecycle.title)
            Button(onClick = onLogout) {
                Text(text = "Log out")
            }
            Button(onClick = onOpenSensitive) {
                Text(text = "Open sensitive screen")
            }
            Button(onClick = onOpenSessionSecurity) {
                Text(text = "Open session security")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    BaseAiProjectTheme {
        HomeScreen()
    }
}
