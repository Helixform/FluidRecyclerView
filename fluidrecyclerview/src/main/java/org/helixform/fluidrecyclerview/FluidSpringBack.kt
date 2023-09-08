package org.helixform.fluidrecyclerview

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.exp

class FluidSpringBack {
    private var c1: Float = 0f
    private var c2: Float = 0f
    private var lambda: Float = 0f

    private val valueThreshold = 0.1f
    private val velocityThreshold = 1e-2f

    data class SpringBackValue(
        val value: Float,
        val isFinished: Boolean
    )

    fun absorb(velocity: Float, distance: Float) {
        lambda = 2.0f * PI.toFloat() / 0.575f
        c1 = distance
        c2 = velocity + lambda * distance
    }

    fun value(time: Float): SpringBackValue {
        val offset = (c1 + c2 * time) * exp(-lambda * time)

        val velocity = velocityAt(time)
        return if (abs(offset) < valueThreshold && abs(velocity) < velocityThreshold) {
            SpringBackValue(0f, true)
        } else {
            SpringBackValue(offset, false)
        }
    }

    private fun velocityAt(time: Float): Float {
        return (c2 - lambda * (c1 + c2 * time)) * exp(-lambda * time)
    }
}