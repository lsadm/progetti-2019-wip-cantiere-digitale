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
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registrazione.*
import kotlinx.android.synthetic.main.fragment_registrazione.editMail
import kotlinx.android.synthetic.main.fragment_registrazione.editPassword
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


    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCrea.setOnClickListener {

            val email = editMail.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val cellulare=editCellulare.text.toString().trim()

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
            if (cellulare.isEmpty()|| cellulare.toIntOrNull()==null) {
                editCellulare.error = "Inserire numero di cellulare valido"
                editCellulare.requestFocus()
                return@setOnClickListener
            }


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
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
                    mAuth.signOut() //dopo la scrittura ritorno al menu iniziale,e do la possibilitá di scegliere se registrare un altro account o di effettuare il login
                    Navigation.findNavController(it).navigateUp()
                    Toast.makeText(context, "Registrazione andata a buon fine", Toast.LENGTH_SHORT).show()

                }


                else { if (task.getException() is FirebaseAuthUserCollisionException) //controlla pre-esistenza

                    Toast.makeText(context, "L'account è già stato registrato", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Campi non validi", Toast.LENGTH_SHORT).show() //altri casi di errore


                }

            }
        }}}

