package com.example.wipcantieredigitale


import android.content.ContentValues
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
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_registrazione.*
import kotlinx.android.synthetic.main.fragment_registrazione.editUsername
import kotlinx.android.synthetic.main.fragment_registrazione.idpass
import kotlinx.android.synthetic.main.fragment_registrazione.editNome
import kotlinx.android.synthetic.main.fragment_registrazione.editCognome
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class RegistrazioneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registrazione, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button2.setOnClickListener {
             if(!editUsername.text.toString().equals("")){
            val database = FirebaseDatabase.getInstance()
           /*      val auth=FirebaseAuth.getInstance()
                 auth.createUserWithEmailAndPassword(idpass.text.toString(),editp.text.toString())*/

                 var uti=true
                 val myRef = database.getReference(editUsername.text.toString() )
                myRef.addValueEventListener(object : ValueEventListener {

                    override fun onCancelled(error: DatabaseError) {
                    }
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        if(dataSnapshot.exists()) {
                             if (uti){ Toast.makeText(activity, "esiste giá", Toast.LENGTH_SHORT).show()
                        }

                        }
                           else
                       {
                           val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh.mm.ss")
                          dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                           dateFormatter.isLenient = false
                           val today = Date()
                           val s = dateFormatter.format(today)


            var nameList= login(editUsername.text.toString(),editp.text.toString(),idpass.text.toString(),editNome.text.toString(),editCognome.text.toString(),mySpinner.getSelectedItem().toString(),s)
            myRef.setValue(nameList)
                           myRef.child("compiti").push()
                           uti=false;

                        Navigation.findNavController(view!!).navigateUp()
                           fragmentManager?.popBackStack()
                          ;}}})}}}}