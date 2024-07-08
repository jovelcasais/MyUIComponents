package com.jovelcasais.myuicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.jovelcasais.my_ui_components.JCSlider
import com.jovelcasais.myuicomponents.ui.theme.MyUIComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyUIComponentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val list = listOf(
                        "0",
                        "1",
                        "2",
                        "3",
                        "4",
                        "5"
                    )
                    Column {
                      /*  JCSlider("This is a test", list, onValueChange = {

                        }, Color.Blue, Color.Gray, Color.Green, Color.Magenta, Color.Red)
*/
                        JCSlider( list, 1f, onValueChange = {

                        })
                    }

                }
            }
        }
    }
}
