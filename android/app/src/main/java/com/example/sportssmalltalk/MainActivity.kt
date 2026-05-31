package com.example.sportssmalltalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sportssmalltalk.ui.SportsSmallTalkApp
import com.example.sportssmalltalk.ui.theme.SportsSmallTalkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsSmallTalkTheme {
                SportsSmallTalkApp()
            }
        }
    }
}
