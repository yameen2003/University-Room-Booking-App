package com.uni.booking

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.uni.booking.database.getBookingDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = getBookingDatabase(applicationContext)
        setContent {
            App(database)
        }
    }
}
