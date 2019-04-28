package com.example.wipcantieredigitale
import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import com.example.wipcantieredigitale.datamodel.login
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.fragment_capo.*

public class CapoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_capo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()
        myRef.addValueEventListener(object : ValueEventListener {
            var lista=ArrayList<login?>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                 for (dsp in dataSnapshot.getChildren()) {
                   lista.add(dsp.getValue(login::class.java)) //add result into array list

                }
                listLavoratori.layoutManager = LinearLayoutManager(activity)
            listLavoratori.adapter = CapoAdapter(lista, requireContext())}

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })


}}
