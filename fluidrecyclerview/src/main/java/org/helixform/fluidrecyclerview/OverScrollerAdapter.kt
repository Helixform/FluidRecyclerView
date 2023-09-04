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
import android.view.animation.Interpolator
import java.lang.RuntimeException
import android.widget.OverScroller as AndroidOverScroller

/**
 * A class that provides the required methods from {@link android.widget.OverScroller}
 * class, of which some operations are replaced by the custom scroller.
 */
class OverScrollerAdapter(
    private val context: Context,
    private val interpolator: Interpolator
) {
    private val systemOverScroller: AndroidOverScroller by lazy {
        AndroidOverScroller(context, interpolator)
    }
    private var isSmoothAnimation = false

    fun computeScrollOffset(): Boolean {
        if (isSmoothAnimation) {
            return systemOverScroller.computeScrollOffset()
        }
        throw RuntimeException("Stub!")
    }

    fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        isSmoothAnimation = true
        systemOverScroller.startScroll(startX, startY, dx, dy, duration)
    }

    fun fling(
        startX: Int, startY: Int, velocityX: Int, velocityY: Int,
        minX: Int, maxX: Int, minY: Int, maxY: Int
    ) {
        isSmoothAnimation = false
        throw RuntimeException("Stub!")
    }

    fun abortAnimation() {
        if (isSmoothAnimation) {
            systemOverScroller.abortAnimation()
        } else {
            throw RuntimeException("Stub!")
        }
    }

    fun getCurrX(): Int {
        if (isSmoothAnimation) {
            return systemOverScroller.currX
        }
        return 0
    }

    fun getCurrY(): Int {
        if (isSmoothAnimation) {
            return systemOverScroller.currY
        }
        return 0
    }

    fun getCurrVelocity(): Float {
        if (isSmoothAnimation) {
            return systemOverScroller.currVelocity
        }
        return 0f
    }

    fun getFinalX(): Int {
        if (isSmoothAnimation) {
            return systemOverScroller.finalX
        }
        return 0
    }

    fun getFinalY(): Int {
        if (isSmoothAnimation) {
            return systemOverScroller.finalY
        }
        return 0
    }

    fun isFinished(): Boolean {
        if (isSmoothAnimation) {
            return systemOverScroller.isFinished
        }
        return true
    }
}