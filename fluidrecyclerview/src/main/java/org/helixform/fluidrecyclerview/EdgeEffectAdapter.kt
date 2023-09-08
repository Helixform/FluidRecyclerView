/*
 * Copyright (C) 2023 Helixform
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helixform.fluidrecyclerview

import android.content.Context
import android.util.TypedValue
import android.view.animation.AnimationUtils
import kotlin.math.roundToInt

/**
 * A class that provides the required methods from {@link android.widget.EdgeEffect}
 * class, of which some operations are replaced by the custom edge effect.
 */
class EdgeEffectAdapter(private val context: Context) {
    private var animationStartTime: Long = -1L
    private var animationElapsedTime: Long = -1L

    private var springBack = FluidSpringBack()
    private var currentDistance = 0f
    var isFinished = true
        private set

    private val displayMetrics = context.resources.displayMetrics

    /**
     * Returns the pull distance needed to be released to remove the showing effect.
     *
     * @return The pull distance that must be released to remove the showing effect.
     */
    fun getDistance(): Float {
        return currentDistance
    }

    /**
     * Call when the effect absorbs an impact at the given velocity.
     * Used when a fling reaches the scroll boundary.
     *
     * @param velocity Velocity at impact in pixels per second.
     */
    fun onAbsorb(velocity: Int) {
        val velocityPxPerMs = velocity / 1000f
        val velocityDpPerMs = velocityPxPerMs / displayMetrics.density

        animationStartTime = AnimationUtils.currentAnimationTimeMillis()
        springBack.absorb(velocityDpPerMs, 0f)
        isFinished = false
    }

    /**
     * A view should call this when content is pulled away from an edge by the user.
     * This will update the state of the current visual effect and its associated animation.
     * The host view should always {@link android.view.View#invalidate()} after this
     * and draw the results accordingly.
     *
     * @param deltaDistance Change in distance since the last call.
     * @return The amount of <code>deltaDistance</code> that was consumed, a number between
     * 0 and <code>deltaDistance</code>.
     */
    fun onPullDistance(deltaDistance: Float): Float {
        return 0f
    }

    fun onRelease() {

    }

    fun computeScrollOffset(): Boolean {
        if (animationStartTime == -1L) {
            return false
        }

        animationElapsedTime = AnimationUtils.currentAnimationTimeMillis() - animationStartTime
        val value = springBack.value(animationElapsedTime.toFloat())
        currentDistance = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value.value, displayMetrics)
        isFinished = value.isFinished

        return !isFinished
    }
}