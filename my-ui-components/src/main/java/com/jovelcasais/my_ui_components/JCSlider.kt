package com.jovelcasais.my_ui_components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @param sliderModifier - Lets you modify the Slider Layout.
 * @param sliderItems Current value of the Slider. Supports different data types.
 * @param value Current value of the slider.
 * @param sliderLineHeight Line height of the slider, default is 10.dp
 * @param paddingTopIndicator Distance padding top between the slider and the label indicator, default is 50.dp
 * @param showAllLabelIndicator Shows all label indicator if true
 * @param showLineIndicator Shows vertical line indicator/divider if true
 * @param sliderColors Let you modify the slider active, inactive and thumb tick color
 * @param indicatorTextSize Text Size font size in float
 */
@Composable
fun JCSlider(sliderModifier: Modifier = Modifier,
             sliderItems: List<String>,
             value: Float, onValueChange: (Int) -> Unit,
             sliderLineHeight : Dp = 10.dp,
             paddingTopIndicator : Dp = 50.dp,
             showAllLabelIndicator : Boolean = true,
             showLineIndicator: Boolean = true,
             sliderColors: SliderColors = customDefaultSliderColors(),
             indicatorTextSize : Float = 40f
) {

    val (sliderValue, setSliderValue) = remember { mutableFloatStateOf(value) }
    val drawPadding = with(LocalDensity.current) { 10.dp.toPx() }
    val textSize = with(LocalDensity.current) { indicatorTextSize }
    val lineHeightPx = with(LocalDensity.current) { sliderLineHeight.toPx() }

    val textPaint = android.graphics.Paint().apply {
        color = if (isSystemInDarkTheme()) 0xffffffff.toInt() else 0xffff47586B.toInt()
        textAlign = android.graphics.Paint.Align.CENTER
        this.textSize = textSize
    }

    Box(contentAlignment = Alignment.Center) {

        Canvas(
            modifier = Modifier
                .height(paddingTopIndicator)
                .fillMaxWidth()
                .padding(
                    top = paddingTopIndicator
                        .div(2)
                        .minus(sliderLineHeight.div(2))
                )
        ) {
            val yStart = 0f
            val distance = (size.width - 2 * drawPadding) / (sliderItems.size - 1)
            val xPositions = sliderItems.indices.map { drawPadding + it * distance }

            sliderItems.forEachIndexed { index, item ->
                val xPosition = xPositions[index]

                if(showLineIndicator){
                    // Draw the line
                    drawLine(
                        color = Color.DarkGray,
                        start = Offset(x = xPosition, y = yStart),
                        end = Offset(x = xPosition, y = lineHeightPx)
                    )
                }

                // Draw the text
                if (showAllLabelIndicator || index % 2 == 1) {
                    this.drawContext.canvas.nativeCanvas.drawText(
                        item,
                        xPosition,
                        size.height,
                        textPaint
                    )
                }
            }
        }
        Slider(
            modifier = sliderModifier.fillMaxWidth(),
            value = sliderValue,
            valueRange = 0f..sliderItems.size.minus(1).toFloat(),
            steps = sliderItems.size.minus(2),
            colors = sliderColors,
            onValueChange = {
                setSliderValue(it)
                onValueChange(it.toInt())
            })
    }
}


@Composable
private fun customDefaultSliderColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color.Transparent,
    inactiveTickColor = Color.Transparent
)