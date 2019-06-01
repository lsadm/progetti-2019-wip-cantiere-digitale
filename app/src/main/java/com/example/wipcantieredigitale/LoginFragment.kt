package com.example.wipcantieredigitale


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.hideKeyboard
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*

 import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.auth.FirebaseAuth


class LoginFragment: Fragment() {

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

        btnSignin.setOnClickListener {

            btnSignin.isClickable=false

            hideKeyboard()

            val email = editmail.text.toString().trim()
            val password = idpass.text.toString().trim()

            if (email.isEmpty()) {
                editmail.error = "email richiesta"
                editmail.requestFocus()
                btnSignin.isClickable=true
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                idpass.error = "6 caratteri richiesti"
                idpass.requestFocus()
                btnSignin.isClickable=true
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if(task.isSuccessful){

                    val user = mAuth!!.currentUser!!.uid
                    val utenti = database.getReference("utenti")
                    val classe = utenti.child(user).child("classe")

                    classe.addValueEventListener(object: ValueEventListener {

                        override fun onCancelled(p0: DatabaseError){
                            //niente
                        }
                                            
                        override fun onDataChange(snapshot: DataSnapshot) {

                            val valore=snapshot.getValue(String::class.java)

                            if (valore == "Capo") {
                                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_capoFragment)
                            }
                            else {
                                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_compitiFragment)
                            }
                        }
                    })
                }
                else
                {
                    Toast.makeText(context , "Errato" , Toast.LENGTH_SHORT).show()
                    btnSignin.isClickable=true
                }
            }
        }
    }
}
