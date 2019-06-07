package com.example.wipcantieredigitale


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*

 import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.auth.FirebaseAuth


class LoginFragment: Fragment() {
    private val PREF_NAME = "wipcantieredigitale"
    private val PREF_MAIL = "Email"
    private val PREF_PASSWORD = "Password"
    private val PREF_AUTOLOGIN = "AutoLogin" //parole chiave sharedpref
    private lateinit var sharedPref: SharedPreferences
    var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = activity!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val autologin = sharedPref.getBoolean(PREF_AUTOLOGIN, false)

        val editor = sharedPref.edit()
        checkAutoLogin.isChecked = autologin
        if( autologin) //se si é scelto di memorizzare i dati,tramite sharePref non sará necessario rinserirli
        {
            editMail.setText(sharedPref.getString(PREF_MAIL,""))
            editPassword.setText(sharedPref.getString(PREF_PASSWORD,""))
        }

        btnSignin.setOnClickListener {
      //tasto per effettuare il login reso non clickabile fino a che la richiesta di login non viene completata
            btnSignin.isClickable=false
              val email = editMail.text.toString().trim()
             val password = editPassword.text.toString().trim()

            if (email.isEmpty()) {
                editMail.error = "email richiesta"
                editMail.requestFocus()
                btnSignin.isClickable=true
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                editPassword.error = "6 caratteri richiesti"
                editPassword.requestFocus()
                btnSignin.isClickable=true
                return@setOnClickListener
            }


            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if(task.isSuccessful){
                    editor.putString(PREF_MAIL,editMail.text.toString())
                    editor.putString(PREF_PASSWORD,editPassword.text.toString())
                    editor.putBoolean(PREF_AUTOLOGIN,checkAutoLogin.isChecked)
                    editor.apply()
                    val currentUID = mAuth!!.currentUser!!.uid
                    val utentiRef = database.getReference("utenti")
                    val ruoloRef = utentiRef.child(currentUID).child("ruolo")

                    ruoloRef.addListenerForSingleValueEvent(object: ValueEventListener {
                        //lettura database per ricavare il luogo,necessario per la gestione dei fragment successivi.
                        override fun onCancelled(p0: DatabaseError){
                            //niente
                        }
                                            
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val valore=snapshot.getValue(String::class.java)

                            if (valore == "Capo") {
                                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_capoFragment)
                            }
                            else {
                                val ruoloFlag = Bundle()
                                ruoloFlag.putString("ruolo dipendente", valore)
                                //passaggio del ruoloFlag,per opportuna gestione.
                                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_compitiFragment,ruoloFlag)
                            }
                        }
                    })
                }
                else
                {
                    Toast.makeText(context , "Password Errata" , Toast.LENGTH_SHORT).show()
                    btnSignin.isClickable=true
                }
            }
        }
    }
}
