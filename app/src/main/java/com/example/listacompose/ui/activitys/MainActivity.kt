package com.example.listacompose.ui.activitys

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listacompose.ui.screens.ListScreen
import com.example.listacompose.ui.theme.ListaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App(onClickFloatingActionButton = {
                        startActivity(Intent(this, FormActivity::class.java))
                    })
                }
            }
        }
    }
}

@Composable
fun App(onClickFloatingActionButton: () -> Unit = {}) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onClickFloatingActionButton) {
                Icon(Icons.Rounded.Add, contentDescription = "null")
            }
        }
    ) {
        ListScreen(modifier = Modifier.padding(it))
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_MASK)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListaComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            App()
        }
    }
}
