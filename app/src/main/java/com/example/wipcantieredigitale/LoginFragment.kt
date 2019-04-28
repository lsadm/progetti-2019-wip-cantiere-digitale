package com.example.wipcantieredigitale


import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editUsername
import kotlinx.android.synthetic.main.fragment_registrazione.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




class LoginFragment :   Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)


        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference(editUsername.text.toString()).child("2")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    Log.d(TAG, "Value is: " + value!!)
                    if(value=="Capo")
                        btnLogin.setOnClickListener {

                            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_capoFragment)}
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })

    //TODO creare schermata per la registrazione del lavoratore o capo//
            btnSignup.setOnClickListener{Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registrazioneFragment)}
    }}