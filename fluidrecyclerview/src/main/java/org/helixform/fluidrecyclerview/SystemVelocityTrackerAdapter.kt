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

import android.view.MotionEvent
import android.view.VelocityTracker as AndroidVelocityTracker

/**
 * Adapter class for implementing {@link VelocityTracker} interface
 * for {@link android.view.VelocityTracker} class.
 */
class SystemVelocityTrackerAdapter: VelocityTracker {
    private val velocityTracker = AndroidVelocityTracker.obtain()

    override fun addMovement(event: MotionEvent) {
        velocityTracker.addMovement(event)
    }

    override fun clear() {
        velocityTracker.clear()
    }

    override fun computeCurrentVelocity(units: Int, maxVelocity: Float) {
        velocityTracker.computeCurrentVelocity(units, maxVelocity)
    }

    override fun getXVelocity(id: Int): Float = velocityTracker.getXVelocity(id)

    override fun getYVelocity(id: Int): Float = velocityTracker.getYVelocity(id)
}