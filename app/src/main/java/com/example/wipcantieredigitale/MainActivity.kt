package com.example.wipcantieredigitale

 import android.app.Activity
 import android.os.Bundle
 import android.support.v4.app.ActivityCompat
 import android.support.v7.app.AppCompatActivity

 import com.example.wipcantieredigitale.datamodel.hideKeyboard
 import com.example.wipcantieredigitale.datamodel.login


class MainActivity :  AppCompatActivity() {
protected var globalL:login?=null
   public fun setL(a: login?){
    globalL=a}

    fun init(){
        globalL=login()}


   public fun getL():login?{
        return globalL
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideKeyboard()
        init()}


     }

