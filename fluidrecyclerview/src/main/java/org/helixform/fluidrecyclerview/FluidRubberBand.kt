package org.helixform.fluidrecyclerview

class FluidRubberBand {
    companion object {
        private const val RUBBER_BAND_COEFFICIENT = 0.55f

        fun offset(distance: Float, range: Float): Float {
            // Check if offset and range are positive.
            if (distance < 0f || range <= 0f) {
                return 0f
            }
            // The calculated offset of  always stays within the range.
            return (1f - (1f / (distance / range * RUBBER_BAND_COEFFICIENT + 1f))) * range
        }

        fun distance(offset: Float, range: Float): Float {
            if (offset < 0f || range < 0f) {
                return 0f
            }
            // The offset cannot be greater than or equal to the range.
            // We subtract a very small value to ensure that the offset does not out of range.
            val clampedOffset = offset.coerceAtMost(range - 0.00001f)
            return (range * clampedOffset / (range - clampedOffset)) / RUBBER_BAND_COEFFICIENT
        }
    }
}