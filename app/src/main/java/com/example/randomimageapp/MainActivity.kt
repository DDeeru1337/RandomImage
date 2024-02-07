package com.example.randomimageapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.randomimageapp.ui.theme.RandomImageAppTheme
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomImageAppTheme {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        var randomImage = remember { mutableStateOf(
                            "https://images.unsplash.com/photo-1706954226305-3d6abf829f08?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=150&ixid=MnwxfDB8MXxyYW5kb218MHx8fHx8fHx8MTcwNzMxNTUyOQ&ixlib=rb-4.0.3&q=80&w=500"
                        ).value }
                        AsyncImage(
                            modifier = Modifier
                                .size(600.dp),
                            model = randomImage,
                            contentDescription = null)
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(Color.DarkGray),
                            onClick = {
                                lifecycleScope.launch {
                                    val response = try {
                                        RetrofitInstance.api.getImage()
                                    } catch (e: IOException) {
                                        Log.e(TAG, "IOException, you need to check internet")
                                        return@launch
                                    } catch (e: HttpException) {
                                        Log.e(TAG, "HttpException, incorrect response")
                                        return@launch
                                    }
                                    if (response.isSuccessful && response.body() != null) {
                                        randomImage = mutableStateOf(response.body()!!.url.toString()).value
                                        Log.e(TAG, "$randomImage")
                                    } else {
                                        Log.e(TAG, "Response not successful")
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "Change!"
                            )
                        }
                    }
                }
            }
        }
    }
}

