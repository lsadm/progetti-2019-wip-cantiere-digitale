package com.example.wipcantieredigitale


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
 import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.compito
import com.example.wipcantieredigitale.datamodel.hideKeyboard
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_aggiungi_compito.*


class AggiungiCompitoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aggiungi_compito, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FirebaseDatabase.getInstance()
       hideKeyboard()
        idDone.setOnClickListener{
            if(!idAggiungiCompito.text.toString().equals(""))
        arguments?.let {
            val prova: Utente? = it.getParcelable("scelta")
            prova?.let {
                prova.username=it.username
            }
            val myRef = database.getReference(prova!!.username)
            myRef.addListenerForSingleValueEvent (object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val newRef=myRef.child("compiti").child(idAggiungiCompito.text.toString())
                    val newc=compito(idAggiungiCompito.text.toString(),idDescrizione.text.toString())
                    newRef.setValue(newc)
                    Navigation.findNavController(view).navigateUp()
                }

                override  fun onCancelled(error: DatabaseError) {

                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())} })}
            else
                Toast.makeText(activity, "Campo non valido", Toast.LENGTH_SHORT).show()
        } }}

