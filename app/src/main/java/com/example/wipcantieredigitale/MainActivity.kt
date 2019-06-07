package com.example.wipcantieredigitale

 import android.app.Activity
 import android.content.Context
  import android.os.Bundle
 import android.support.v4.app.Fragment
 import android.support.v7.app.AppCompatActivity
 import android.view.View
 import android.view.inputmethod.InputMethodManager
 import android.widget.Toast


class MainActivity :  AppCompatActivity() {
    private var TriploBack = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideKeyboard()//
    }


    override fun onBackPressed() {
        if (TriploBack == 3) {
            finish()
        } else {
            TriploBack++
            super.onBackPressed()
        }



        if (TriploBack == 2)
            Toast.makeText(this, "Ripremere Back per terminare l'applicazione", Toast.LENGTH_SHORT).show()

        android.os.Handler().postDelayed({
            this.TriploBack = 0

        }, 1500)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


