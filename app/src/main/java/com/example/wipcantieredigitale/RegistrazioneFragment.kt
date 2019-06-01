package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_registrazione.*
import kotlinx.android.synthetic.main.fragment_registrazione.editUsername
import kotlinx.android.synthetic.main.fragment_registrazione.idpass
import kotlinx.android.synthetic.main.fragment_registrazione.editNome
import kotlinx.android.synthetic.main.fragment_registrazione.editCognome
import java.text.SimpleDateFormat
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
        btnCrea.setOnClickListener {

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

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->

                if(task.isSuccessful){

                    val utentiRef = database.getReference("utenti")
                    val currentUid = mAuth!!.currentUser!!.uid
                    val currentUidRef = utentiRef.child(currentUid)

                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh.mm.ss")
                    dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                    dateFormatter.isLenient = false
                    val today = Date()
                    val s = dateFormatter.format(today)

                    var datiUtente = Utente(editUsername.text.toString(),editp.text.toString(),idpass.text.toString(),editNome.text.toString(),editCognome.text.toString(),mySpinner.getSelectedItem().toString(),s)
                    currentUidRef.setValue(datiUtente)
                    //currentUidRef.child("compiti").push()

                    val classeRef = utentiRef.child(currentUid).child("classe")

                    classeRef.addValueEventListener(object: ValueEventListener {

                        override fun onCancelled(p0: DatabaseError){
                            //niente
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {

                            val valore=snapshot.getValue(String::class.java)

                            if (valore == "Capo") {
                                Navigation.findNavController(it).navigate(R.id.action_registrazioneFragment_to_capoFragment2)
                            }
                            else {
                                Navigation.findNavController(it).navigate(R.id.action_registrazioneFragment_to_compitiFragment)
                            }
                        }
                    })
                }
                else {
                    Toast.makeText(context , "L'account è già stato registrato" , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}




















            /*


                   */










