package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.ui.navigation.AppNavigation
import com.picpay.desafio.android.ui.theme.PicPayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicPayTheme  {
                AppNavigation()
            }
        }
    }
}
