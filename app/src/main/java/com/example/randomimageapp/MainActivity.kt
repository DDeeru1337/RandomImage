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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        var randomImage = "https://random.imagecdn.app/v1/image?width=500&height=150"
                        AsyncImage(
                            modifier = Modifier
                                .size(600.dp),
                            model = randomImage,
                            contentDescription = null)
                        Button(
                            modifier = Modifier
                                .background(Color.Red),
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
                                        randomImage = response.body().toString()
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

