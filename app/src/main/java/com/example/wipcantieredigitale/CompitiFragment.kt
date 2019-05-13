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
import kotlinx.android.synthetic.main.riga_compito.*

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
          val database = FirebaseDatabase.getInstance()


       val prova=(activity as MainActivity).getL()

        fabChat.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_compitiFragment_to_chatFragment)
        }

        fabAggiungiCompito.setOnClickListener {
                val b=Bundle();
                b.putParcelable("scelta",prova)
                Navigation.findNavController(it).navigate(R.id.action_compitiFragment_to_aggiungiCompitoFragment,b)}
        val myRef = database.getReference(prova!!.username)
            myRef.addListenerForSingleValueEvent (object : ValueEventListener {
                var lista =ArrayList<compito?>()
                var cont=0;

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            if(dataSnapshot.hasChild("compiti")){
                for (dsp in dataSnapshot.child("compiti").getChildren())
                    lista.add(dsp.getValue(compito::class.java))
                for(item in lista )
                    if(item?.done==true)
                        cont++;
                numero.text=cont.toString()
                ListaCompiti.layoutManager = LinearLayoutManager(activity)
                ListaCompiti.adapter = CompitiAdapter (lista, requireContext())
              }

            }
        override  fun onCancelled(error: DatabaseError) {

             Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
        }})}}