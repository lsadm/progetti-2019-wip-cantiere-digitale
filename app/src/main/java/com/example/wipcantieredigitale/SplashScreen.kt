package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import androidx.navigation.Navigation

class SplashScreen : Fragment() {
var esecuzione=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       if(esecuzione==0){
        Handler().postDelayed({
            esecuzione=1
            Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_welcomeFragment)
        }, 1000)

    }
    else{if (getActivity() != null) {
           getActivity()!!.getSupportFragmentManager().beginTransaction().remove(this).commit();
            try {
                 activity!!.finish()
           }  catch (e:Exception) {
               e.printStackTrace()
           }
       }

}}}