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
import kotlinx.android.synthetic.main.fragment_registrazione.editCellulare
import kotlinx.android.synthetic.main.fragment_registrazione.editMail
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

            val email = editMail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isEmpty()) {
                editMail.error = "Email richiesta"
                editMail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                editMail.error = "6 caratteri richiesti"
                editMail.requestFocus()
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->

                if(task.isSuccessful){

                    val currentUID = mAuth!!.currentUser!!.uid
                    val utentiRef = database.getReference("utenti")
                    val currentUIDRef = utentiRef.child(currentUID)

                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh.mm.ss")
                    dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                    dateFormatter.isLenient = false
                    val today = Date()
                    val s = dateFormatter.format(today)

                    var datiUtente = Utente(editNome.text.toString(),editCognome.text.toString(),editCellulare.text.toString(),editMail.text.toString(),editPassword.text.toString(),mySpinner.getSelectedItem().toString(),s)
                    currentUIDRef.setValue(datiUtente)

                    val ruoloRef = utentiRef.child(currentUID).child("ruolo")

                    ruoloRef.addValueEventListener(object: ValueEventListener {

                        override fun onCancelled(p0: DatabaseError){
                            //niente
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {

                            val valore=snapshot.getValue(String::class.java)

                            if (valore == "Capo") {
                                Navigation.findNavController(it).navigate(R.id.action_registrazioneFragment_to_capoFragment2)
                            }
                            else {
                                val ruoloFlag = Bundle()
                                ruoloFlag.putString("ruolo dipendente", valore)
                                Navigation.findNavController(it).navigate(R.id.action_registrazioneFragment_to_compitiFragment,ruoloFlag)
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










