package com.example.wipcantieredigitale


import android.content.ContentValues.TAG
import android.os.Bundle
 import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.hideKeyboard
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editUsername
 import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.example.wipcantieredigitale.datamodel.login


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

                        btnSignin.setOnClickListener {
                            hideKeyboard()
                            if(!editUsername.text.toString().equals("")){

                                val myRef = database.getReference(editUsername.text.toString())
                             myRef.addValueEventListener(object : ValueEventListener {
                                 override fun onDataChange(dataSnapshot: DataSnapshot) {
                                     val value:login? = dataSnapshot.getValue(login::class.java)
                                    if (idpass.text.toString()==value?.password) {
                                         if (value.classe.equals("Capo"))

                                             Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_capoFragment)
                                         else{

                                             val b = Bundle()
                                             b.putParcelable("scelta", value)
                                              Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_compitiFragment,b)}
                                     }

                                 }

                                 override fun onCancelled(error: DatabaseError) {
                                     // Failed to read value
                                     Log.w(TAG, "Failed to read value.", error.toException())
                                 }
                             })
                            }
    }}}