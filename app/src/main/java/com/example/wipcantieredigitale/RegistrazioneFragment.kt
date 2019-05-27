package com.example.wipcantieredigitale


import android.content.ContentValues
import android.content.Intent
import android.icu.text.TimeZoneFormat
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.login
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.widget.Toast
import com.example.wipcantieredigitale.datamodel.hideKeyboard
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.fragment_registrazione.*
import kotlinx.android.synthetic.main.fragment_registrazione.editUsername
import kotlinx.android.synthetic.main.fragment_registrazione.idpass
import kotlinx.android.synthetic.main.fragment_registrazione.editNome
import kotlinx.android.synthetic.main.fragment_registrazione.editCognome
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class RegistrazioneFragment : Fragment() {

    var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registrazione, container, false)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button2.setOnClickListener {

            val email = idpass.text.toString().trim()
            val password = editp.text.toString().trim()

            if (email.isEmpty()) {
                idpass.error = "Email richiesta"
                idpass.requestFocus()
                return@setOnClickListener

            }

            if (password.isEmpty() || password.length < 6) {
                idpass.error = "6 caratteri richiesti"
                idpass.requestFocus()
                return@setOnClickListener
            }



            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->

                    if(task.isSuccessful){

                        val utenti = database.getReference("utenti")

                        val user = mAuth!!.currentUser!!.uid


                        val myRef = utenti.child(user)




                                val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh.mm.ss")
                                dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                                dateFormatter.isLenient = false
                                val today = Date()
                                val s = dateFormatter.format(today)


                                var nameList= login(editUsername.text.toString(),editp.text.toString(),idpass.text.toString(),editNome.text.toString(),editCognome.text.toString(),mySpinner.getSelectedItem().toString(),s)
                                myRef.setValue(nameList)
                                myRef.child("compiti").push()


                                Navigation.findNavController(it).navigateUp()
                                fragmentManager?.popBackStack()
                                ;}


                else{
                        Toast.makeText(context , "L'account è già stato registrato" , Toast.LENGTH_SHORT).show()
                    }}}}}




















            /*


                   */










