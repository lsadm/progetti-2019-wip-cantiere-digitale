package com.example.wipcantieredigitale

 import android.os.Bundle
 import android.support.v7.app.AppCompatActivity

 import com.example.wipcantieredigitale.datamodel.hideKeyboard
 import com.example.wipcantieredigitale.datamodel.Utente


class MainActivity :  AppCompatActivity() {
protected var globalL:Utente?=null
   public fun setL(a: Utente?){
    globalL=a}

    fun init(){
        globalL=Utente()}


   public fun getL():Utente?{
        return globalL
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideKeyboard()
        init()}


     }

