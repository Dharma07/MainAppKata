package com.training.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.training.findmyip.findmyip.FindMyIPScreen
import com.training.findmyip.findmyip.MyIPViewModel
import com.training.findmyip.findmyip.MyIPViewModelFactory
import com.training.findmyip.ui.theme.FindMyIPTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MyIPViewModel by viewModels {
        MyIPViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIPTheme {
                FindMyIPScreen(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindMyIPTheme {
    }
}

