package com.ingloriousmind.compose.playground

import android.animation.TimeInterpolator
import android.view.animation.BounceInterpolator
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

fun TimeInterpolator.asEasing() = Easing { x -> getInterpolation(x) }

val AnticipateEasing = CubicBezierEasing(.68f, -0.26f, .81f, .79f)
val BounceEasing = BounceInterpolator().asEasing()
