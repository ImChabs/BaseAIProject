package com.example.baseaiproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baseaiproject.ui.theme.BaseAiProjectTheme

sealed interface AppAuthState {
    data object SessionChecking : AppAuthState

    data class Unauthenticated(
        val entryReason: UnauthenticatedEntryReason = UnauthenticatedEntryReason.Default
    ) : AppAuthState

    data class Authenticated(
        val sessionLifecycle: ProtectedSessionLifecycle = ProtectedSessionLifecycle.Active
    ) : AppAuthState
}

enum class UnauthenticatedEntryReason {
    Default,
    StartupConnectivityIssue,
    ForcedLogout
}

enum class AuthenticatedDestination {
    Home,
    Sensitive,
    SessionSecurity
}

sealed interface SessionCheckAction {
    data object ContinueUnauthenticated : SessionCheckAction

    data object RestoreAuthenticatedSession : SessionCheckAction

    data object RestoreExpiredSession : SessionCheckAction

    data object ReportConnectivityIssue : SessionCheckAction
}

sealed interface SessionSecurityAction {
    data object MarkSessionActive : SessionSecurityAction

    data object MarkAccessExpired : SessionSecurityAction

    data object StartRefresh : SessionSecurityAction

    data object CompleteRefresh : SessionSecurityAction

    data object FailRefresh : SessionSecurityAction
}

enum class ProtectedSessionLifecycle(
    val title: String,
    val description: String
) {
    Active(
        title = "Active session",
        description = "Protected access is currently available."
    ),
    AccessExpired(
        title = "Access expired",
        description = "Protected access is expired and can move into a placeholder refresh attempt."
    ),
    Refreshing(
        title = "Refresh in progress",
        description = "A placeholder refresh attempt is recovering the session before either success or forced logout."
    ),
    Unrecoverable(
        title = "Session no longer recoverable",
        description = "This placeholder state represents session-ending auth loss before real token handling exists."
    )
}

@Composable
fun AppRoot(
    modifier: Modifier = Modifier,
    authState: AppAuthState = AppAuthState.SessionChecking,
    authenticatedDestination: AuthenticatedDestination = AuthenticatedDestination.Home,
    onSessionCheckAction: (SessionCheckAction) -> Unit = {},
    onLoginComplete: () -> Unit = {},
    onLogout: () -> Unit = {},
    onAuthenticatedDestinationChange: (AuthenticatedDestination) -> Unit = {},
    onSessionSecurityAction: (SessionSecurityAction) -> Unit = {}
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        when (authState) {
            AppAuthState.SessionChecking -> {
                SessionCheckScreen(
                    modifier = Modifier.padding(innerPadding),
                    onAction = onSessionCheckAction
                )
            }

            is AppAuthState.Authenticated -> {
                when (authenticatedDestination) {
                    AuthenticatedDestination.Home -> {
                        HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            sessionLifecycle = authState.sessionLifecycle,
                            onLogout = onLogout,
                            onOpenSensitive = {
                                onAuthenticatedDestinationChange(AuthenticatedDestination.Sensitive)
                            },
                            onOpenSessionSecurity = {
                                onAuthenticatedDestinationChange(AuthenticatedDestination.SessionSecurity)
                            }
                        )
                    }

                    AuthenticatedDestination.Sensitive -> {
                        SensitiveScreen(
                            modifier = Modifier.padding(innerPadding),
                            sessionLifecycle = authState.sessionLifecycle,
                            onLogout = onLogout,
                            onOpenHome = {
                                onAuthenticatedDestinationChange(AuthenticatedDestination.Home)
                            },
                            onOpenSessionSecurity = {
                                onAuthenticatedDestinationChange(AuthenticatedDestination.SessionSecurity)
                            }
                        )
                    }

                    AuthenticatedDestination.SessionSecurity -> {
                        SessionSecurityScreen(
                            modifier = Modifier.padding(innerPadding),
                            sessionLifecycle = authState.sessionLifecycle,
                            onAction = onSessionSecurityAction,
                            onLogout = onLogout,
                            onOpenHome = {
                                onAuthenticatedDestinationChange(AuthenticatedDestination.Home)
                            },
                            onOpenSensitive = {
                                onAuthenticatedDestinationChange(AuthenticatedDestination.Sensitive)
                            }
                        )
                    }
                }
            }

            is AppAuthState.Unauthenticated -> {
                LoginScreen(
                    modifier = Modifier.padding(innerPadding),
                    entryReason = authState.entryReason,
                    onContinue = onLoginComplete
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootSessionCheckingPreview() {
    BaseAiProjectTheme {
        AppRoot(authState = AppAuthState.SessionChecking)
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootAuthenticatedPreview() {
    BaseAiProjectTheme {
        AppRoot(authState = AppAuthState.Authenticated())
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootSensitivePreview() {
    BaseAiProjectTheme {
        AppRoot(
            authState = AppAuthState.Authenticated(),
            authenticatedDestination = AuthenticatedDestination.Sensitive
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootSessionSecurityPreview() {
    BaseAiProjectTheme {
        AppRoot(
            authState = AppAuthState.Authenticated(ProtectedSessionLifecycle.AccessExpired),
            authenticatedDestination = AuthenticatedDestination.SessionSecurity
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootUnauthenticatedPreview() {
    BaseAiProjectTheme {
        AppRoot(authState = AppAuthState.Unauthenticated())
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootForcedLogoutPreview() {
    BaseAiProjectTheme {
        AppRoot(
            authState = AppAuthState.Unauthenticated(
                entryReason = UnauthenticatedEntryReason.ForcedLogout
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootStartupConnectivityIssuePreview() {
    BaseAiProjectTheme {
        AppRoot(
            authState = AppAuthState.Unauthenticated(
                entryReason = UnauthenticatedEntryReason.StartupConnectivityIssue
            )
        )
    }
}
