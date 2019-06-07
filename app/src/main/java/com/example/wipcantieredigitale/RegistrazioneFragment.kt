package com.example.wipcantieredigitale


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_registrazione.*
import java.text.SimpleDateFormat
import java.util.*


class RegistrazioneFragment : Fragment() {
    var registrazione=0 //check per rimuovere fragment registrazione ed evitarne doppie in 1 sessione
    var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registrazione, container, false)
    }


    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (registrazione==1){ //ritorna al WelcomeFragment se giá mi sono registrato 1 volta,e riapre eventualmente una nuova sessone di Registrazione
            activity!!.run { getSupportFragmentManager().popBackStack() }
            getActivity()!!.getSupportFragmentManager().beginTransaction().remove(this).commit();}
        btnCrea.setOnClickListener {

            val email = editMail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isEmpty()) {
                editMail.error = "Email richiesta"
                editMail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                editPassword.error = "6 caratteri richiesti"
                editPassword.requestFocus()
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    registrazione=1
                    val currentUID = mAuth!!.currentUser!!.uid
                    val utentiRef = database.getReference("utenti")
                    val currentUIDRef = utentiRef.child(currentUID)
                    val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh.mm.ss")
                    dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+2"))
                    dateFormatter.isLenient = false
                    val today = Date()
                    val tempoAssunzione = dateFormatter.format(today)

                    val datiUtente = Utente(
                        editNome.text.toString(),
                        editCognome.text.toString(),
                        editCellulare.text.toString(),
                        editMail.text.toString(),
                        editPassword.text.toString(),
                        SpinnerRuolo.getSelectedItem().toString(),
                        tempoAssunzione
                    )
                    currentUIDRef.setValue(datiUtente) //salva nel database


                            if (SpinnerRuolo.getSelectedItem().toString() == "Capo") {
                                Navigation.findNavController(it)
                                    .navigate(R.id.action_registrazioneFragment_to_capoFragment2)

                            } else {
                                val ruoloFlag = Bundle()
                                ruoloFlag.putString("ruolo dipendente", SpinnerRuolo.getSelectedItem().toString())
                                Navigation.findNavController(it)
                                    .navigate(R.id.action_registrazioneFragment_to_compitiFragment, ruoloFlag)

                            }}




                 else {
                    Toast.makeText(context, "L'account è già stato registrato", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }}





















            /*


                   */










