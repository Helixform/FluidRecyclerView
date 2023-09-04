package org.helixform.fluidrecyclerview

import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

class FluidScroller {
    companion object {
        const val DECELERATION_RATE_NORMAL = 0.998f
        const val DECELERATION_RATE_FAST = 0.99f
    }

    data class ScrollValue(
        val value: Float,
        val velocity: Float,
        val isFinished: Boolean
    )

    var decelerateRate: Float
    private var initialVelocity: Float = 0f

    private val threshold = 1e-2f

    constructor(decelerateRate: Float = DECELERATION_RATE_NORMAL) {
        this.decelerateRate = decelerateRate
    }

    fun fling(velocity: Float) {
        initialVelocity = velocity
    }

    fun value(time: Float): ScrollValue {
        val velocity = velocityAt(time)
        return ScrollValue(offsetAt(time), velocity, abs(velocity) < threshold)
    }

    fun finalOffset(): Float {
        // A scroll won't happen when the velocity is below the threshold.
        // Meanwhile, Avoiding division by zero.
        if (abs(initialVelocity) < threshold) {
            return 0f
        }
        val t = ln(threshold / abs(initialVelocity)) / ln(decelerateRate)
        return offsetAt(t)
    }

    private fun velocityAt(time: Float): Float {
        return initialVelocity * decelerateRate.pow(time)
    }

    private fun offsetAt(time: Float): Float {
        return initialVelocity * (1f / ln(decelerateRate)) * (decelerateRate.pow(time) - 1f)
    }
}