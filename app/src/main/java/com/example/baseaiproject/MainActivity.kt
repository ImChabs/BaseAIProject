package com.example.baseaiproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.baseaiproject.ui.theme.BaseAiProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseAiProjectTheme {
                var authState by remember { mutableStateOf<AppAuthState>(AppAuthState.SessionChecking) }
                var authenticatedDestination by remember {
                    mutableStateOf(AuthenticatedDestination.Home)
                }
                fun clearSession(entryReason: UnauthenticatedEntryReason) {
                    authenticatedDestination = AuthenticatedDestination.Home
                    authState = AppAuthState.Unauthenticated(entryReason = entryReason)
                }

                fun updateAuthenticatedSessionLifecycle(lifecycle: ProtectedSessionLifecycle) {
                    val currentState = authState
                    if (currentState is AppAuthState.Authenticated) {
                        authState = currentState.copy(sessionLifecycle = lifecycle)
                    }
                }

                AppRoot(
                    authState = authState,
                    authenticatedDestination = authenticatedDestination,
                    onSessionCheckAction = { action ->
                        when (action) {
                            SessionCheckAction.ContinueUnauthenticated -> {
                                clearSession(UnauthenticatedEntryReason.Default)
                            }

                            SessionCheckAction.RestoreAuthenticatedSession -> {
                                authenticatedDestination = AuthenticatedDestination.Home
                                authState = AppAuthState.Authenticated()
                            }

                            SessionCheckAction.RestoreExpiredSession -> {
                                authenticatedDestination = AuthenticatedDestination.SessionSecurity
                                authState = AppAuthState.Authenticated(
                                    sessionLifecycle = ProtectedSessionLifecycle.AccessExpired
                                )
                            }

                            SessionCheckAction.ReportConnectivityIssue -> {
                                clearSession(UnauthenticatedEntryReason.StartupConnectivityIssue)
                            }
                        }
                    },
                    onLoginComplete = {
                        authenticatedDestination = AuthenticatedDestination.Home
                        authState = AppAuthState.Authenticated()
                    },
                    onLogout = {
                        clearSession(UnauthenticatedEntryReason.Default)
                    },
                    onAuthenticatedDestinationChange = { destination ->
                        if (authState is AppAuthState.Authenticated) {
                            authenticatedDestination = destination
                        }
                    },
                    onSessionSecurityAction = { action ->
                        when (action) {
                            SessionSecurityAction.MarkSessionActive -> {
                                updateAuthenticatedSessionLifecycle(ProtectedSessionLifecycle.Active)
                            }

                            SessionSecurityAction.MarkAccessExpired -> {
                                updateAuthenticatedSessionLifecycle(ProtectedSessionLifecycle.AccessExpired)
                            }

                            SessionSecurityAction.StartRefresh -> {
                                updateAuthenticatedSessionLifecycle(ProtectedSessionLifecycle.Refreshing)
                            }

                            SessionSecurityAction.CompleteRefresh -> {
                                updateAuthenticatedSessionLifecycle(ProtectedSessionLifecycle.Active)
                            }

                            SessionSecurityAction.FailRefresh -> {
                                clearSession(UnauthenticatedEntryReason.ForcedLogout)
                            }
                        }
                    }
                )
            }
        }
    }
}
