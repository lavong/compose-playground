package com.ingloriousmind.compose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ingloriousmind.compose.playground.explode.ExplodeAnimationScreen
import com.ingloriousmind.compose.playground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                ExplodeAnimationScreen()
            }
        }
    }
}
