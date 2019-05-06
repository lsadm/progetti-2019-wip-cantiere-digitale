package com.example.wipcantieredigitale


import android.content.ContentValues
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.compito
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_aggiungi_compito.*
import kotlinx.android.synthetic.main.fragment_compito.*
import kotlinx.android.synthetic.main.fragment_compito.idDescrizione
import kotlinx.android.synthetic.main.fragment_compito.idDone

class CompitoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compito, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val prova: compito? = it.getParcelable("compito")
            prova?.let {
                 nomeCompito.text = it.nome
                idDescrizione.text = it.desc
             }
            idDone.setOnClickListener{
        prova?.done=true
            val database=FirebaseDatabase.getInstance( )
            val myRef = database.getReference((activity as MainActivity).getL()!!.username ).child("compiti").child(prova!!.nome)
            myRef.addListenerForSingleValueEvent (object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    myRef.setValue(prova)
                    Navigation.findNavController(view!!).navigateUp()
                }

                override  fun onCancelled(error: DatabaseError) {

                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())} })}

             }
        }
    }
