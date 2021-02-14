package eu.vmpay.random.city.tools

import android.graphics.Color
import androidx.annotation.ColorInt

enum class FixedColors(val color: Int) {
    UNKNOWN(Color.GRAY),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
}

@ColorInt
fun String.getFixedColor(): Int {
    return FixedColors.values().firstOrNull { it.name.equals(this, true) }?.color
            ?: FixedColors.UNKNOWN.color
}
