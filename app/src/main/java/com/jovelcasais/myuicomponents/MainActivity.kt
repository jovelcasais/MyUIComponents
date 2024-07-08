package com.jovelcasais.myuicomponents

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jovelcasais.my_ui_components.JCSlider
import com.jovelcasais.myuicomponents.ui.theme.MyUIComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUIComponentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    val sliderItems1 = listOf("0",  "1",  "2", "3", "4", "5")

                    val sliderColors : SliderColors = SliderDefaults.colors(
                        activeTickColor = Color.Transparent,
                        inactiveTickColor = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Column(modifier = Modifier.padding(20.dp)) {

                        Text(text = "How you feeling physically?")

                        JCSlider(sliderItems = sliderItems1, value = 1f, onValueChange = {
                            Log.v("MySliderSelectedValue", it.toString())
                        }, showAllLabelIndicator = true, sliderColors = sliderColors)

                    }
                }
            }
        }
    }
}
