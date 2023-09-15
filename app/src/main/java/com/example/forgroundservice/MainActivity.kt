package com.example.forgroundservice

import android.Manifest.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.forgroundservice.service.Actions
import com.example.forgroundservice.service.AirPlaneModeReceiver
import com.example.forgroundservice.service.RunningService
import com.example.forgroundservice.ui.theme.ForgroundServiceTheme
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {
    private val airPlaneModeReceiver = AirPlaneModeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(airPlaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission.POST_NOTIFICATIONS), 0
            )
        }
        setContent {
            ForgroundServiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            Intent(applicationContext, Actions.START.toString()::class.java).also {
                                startService(it)
                            }
                        }) {
                            Text(text = "Start Service")
                        }
                        Button(onClick = {
                            Intent(applicationContext, Actions.STOP.toString()::class.java).also {
                                startService(it)
                            }
                        }) {
                            Text(text = "Stop Service")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ForgroundServiceTheme {
        Greeting("Android")
    }
}