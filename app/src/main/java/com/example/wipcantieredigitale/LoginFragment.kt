package com.example.wipcantieredigitale


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.hideKeyboard
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editUsername
 import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.example.wipcantieredigitale.datamodel.login
import com.google.firebase.auth.FirebaseAuth


class LoginFragment :   Fragment() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


        mAuth = FirebaseAuth.getInstance()

                        btnSignin.setOnClickListener {
                            hideKeyboard()

                            val email=editmail.text.toString().trim()
                            val password=idpass.text.toString().trim()

                            if(email.isEmpty()){
                                editmail.error = "email richiesta"
                                editmail.requestFocus()
                                return@setOnClickListener
                            }

                            if(password.isEmpty() || password.length < 6){
                                idpass.error = "6 caratteri richiesti"
                                idpass.requestFocus()
                                return@setOnClickListener
                            }


                            mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if(task.isSuccessful){

                                        val database = FirebaseDatabase.getInstance()

                                        val myRef = database.getReference(editUsername.text.toString())
                                        myRef.addListenerForSingleValueEvent (object : ValueEventListener {

                                            override fun onCancelled(error: DatabaseError) {
                                                // Failed to read value
                                                Log.w(TAG, "Failed to read value.", error.toException())
                                            }

                                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                val value:login?=dataSnapshot.getValue(login::class.java)
                                                (activity as MainActivity).setL(value)

                                                    if (value?.classe.equals("Capo")) {

                                                        Navigation.findNavController(it)
                                                            .navigate(R.id.action_loginFragment_to_capoFragment)
                                                    }
                                                    else{

                                                        val b = Bundle()
                                                        b.putParcelable("scelta", value)
                                                        Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_compitiFragment,b)
                                                    }}




                                    }) } else{
                                        Toast.makeText(context , "Errato" , Toast.LENGTH_SHORT).show()
                                        }


                                }




                           }}}



/*  if(!editUsername.text.toString().equals("")){







                             })}      */