package com.example.wipcantieredigitale
import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.compito
import com.example.wipcantieredigitale.datamodel.login
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.fragment_compiti.*
import kotlinx.android.synthetic.main.fragment_compito.*

/**
 *
 */
   class CompitiFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compiti, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val prova: login? = it.getParcelable("scelta")
            prova?.let {
                prova?.username = it.username
            }
        fabAggiungiCompito.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_compitiFragment_to_aggiungiCompitoFragment)}
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(prova!!.username).child("compiti")
            myRef.addValueEventListener(object : ValueEventListener {
                var lista=ArrayList<compito?>()
        override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dsp in dataSnapshot.getChildren()) {
                    lista.add(dsp.getValue(compito::class.java))}
            listaCompiti.layoutManager = LinearLayoutManager(activity)
            listaCompiti.adapter = CompitiAdapter (lista, requireContext())}
        override  fun onCancelled(error: DatabaseError) {
             Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
        }})}}}