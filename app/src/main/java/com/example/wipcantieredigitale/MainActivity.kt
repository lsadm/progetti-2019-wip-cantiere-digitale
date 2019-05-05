package com.example.wipcantieredigitale

 import android.support.v7.app.AppCompatActivity
import android.os.Bundle
 import android.view.View
 import androidx.navigation.Navigation
 import com.example.wipcantieredigitale.datamodel.hideKeyboard


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideKeyboard()

    }


     }
