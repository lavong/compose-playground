package com.ingloriousmind.compose.playground.explode

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ingloriousmind.compose.playground.AnticipateEasing
import com.ingloriousmind.compose.playground.ui.theme.ComposePlaygroundTheme

@Composable
fun ExplodeAnimationScreen() {
    var proceed by remember { mutableStateOf(false) }

    val transition = updateTransition(proceed, label = "explosion")
    val animatedScale = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 300, easing = AnticipateEasing) },
        label = "circle scale",
    ) { if (it) 8f else 1f }
    val animatedAlpha = transition.animateFloat(label = "checkmark visibility") { if (it) 0f else 1f }

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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        ExplodeAnimationScreen()
    }
}
