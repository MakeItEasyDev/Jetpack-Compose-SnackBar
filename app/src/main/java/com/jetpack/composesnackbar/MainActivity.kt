package com.jetpack.composesnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.jetpack.composesnackbar.ui.theme.ComposeSnackBarTheme
import com.jetpack.composesnackbar.ui.theme.Purple500
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSnackBarTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SetSnackBar()
                }
            }
        }
    }
}

@Composable
fun SetSnackBar() {
    val scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = SnackbarHostState()
    )

    val visibleState: MutableState<Boolean> = remember { mutableStateOf(false) }

    val snackBarMessage: MutableState<String> = remember { mutableStateOf("") }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Compose SnackBar",
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                backgroundColor = Purple500
            )
        },
        content = {
            ShowSnackBar(visibleState, snackBarMessage)
        },
        backgroundColor = Color.White,
        snackbarHost = {
            if (visibleState.value) {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                visibleState.value = false
                            }
                        ) {
                            Text(
                                text = "Hide",
                                color = Color.Yellow
                            )
                        }
                    },
                    content = {
                        Text(
                            text = snackBarMessage.value
                        )
                    },
                    backgroundColor = Color(0xFF2D852D)
                )
            }
        }
    )
}

@Composable
fun ShowSnackBar(
    visibleState: MutableState<Boolean>,
    snackBarMessage: MutableState<String>
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                scope.launch {
                    visibleState.value = true
                    snackBarMessage.value = "File Upload Successful"
                }
            }
        ) {
            Text(
                text = "Show SnackBar"
            )
        }
    }
}





















