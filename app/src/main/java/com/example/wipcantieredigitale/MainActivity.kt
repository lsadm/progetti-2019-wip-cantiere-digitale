package com.example.wipcantieredigitale

 import android.os.Bundle
 import android.support.v7.app.AppCompatActivity
 import android.widget.Toast
 import androidx.navigation.Navigation
 import java.util.logging.Handler


class MainActivity :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private var TriploBack =0
    override fun onBackPressed() {
        if (TriploBack==3) {
            finish();
        }else
        {TriploBack++

                super.onBackPressed()
            }



        if(TriploBack==2)
        Toast.makeText(this, "Ripremere Back per terminare l'applicazione", Toast.LENGTH_SHORT).show()

        android.os.Handler().postDelayed({
          this.TriploBack=0

        }, 2000)
    }
}

