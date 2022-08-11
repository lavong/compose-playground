package com.ingloriousmind.compose.playground

import android.animation.TimeInterpolator
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

fun TimeInterpolator.toEasing() = Easing { x -> getInterpolation(x) }

@Composable
fun ExplodeAnimationScreen() {
    var proceed by remember { mutableStateOf(false) }
//    val animatedScale = animateFloatAsState(if (proceed) 10f else 1f, tween(800, easing = LinearOutSlowInEasing)) {
//        println("done animating")
//    }
//    val animatedScale = animateFloatAsState(if (proceed) 10f else 1f, tween(800, easing = CubicBezierEasing(1.5f, 1f, .8f, 1.5f))) {
//        println("done animating")
//    }
//    val animatedScale = animateFloatAsState(if (proceed) 10f else 1f, tween(800, easing = BounceInterpolator().toEasing())) {
//        println("done animating")
//    }

    val transition = updateTransition(proceed, label = "")
    val animatedScale = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 800, easing = LinearOutSlowInEasing) },
        label = "",
    ) {
        if (it) 10f else 1f
    }
    val animatedAlpha = transition.animateFloat({ tween(durationMillis = 200) }, label = "") {
        if (it) 0f else 1f
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .scale(animatedScale.value)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .clickable { proceed = !proceed }
            )
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .alpha(animatedAlpha.value),
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        ExplodeAnimationScreen()
    }
}
