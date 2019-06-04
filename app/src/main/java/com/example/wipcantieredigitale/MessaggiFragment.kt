package com.example.wipcantieredigitale


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wipcantieredigitale.datamodel.Messaggio
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_messaggi.*

class MessaggiFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messaggi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myRef = FirebaseDatabase.getInstance().getReference("chat")
        var listaMess = ArrayList<Messaggio?>()
         myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                 arguments?.let {
                     val dipendente: Utente? = it.getParcelable("dipendente scelto")
                     for (dsp in dataSnapshot.getChildren())
                         if (dsp.getValue(Messaggio::class.java)!!.destinatario == dipendente!!.mail)
                             listaMess.add(dsp.getValue(Messaggio::class.java))

                 }
                      listMessaggi.layoutManager = LinearLayoutManager(activity)
                     listMessaggi.adapter = MessaggiAdapter(listaMess, requireContext())


                           }
                     override fun onCancelled(error: DatabaseError) {
                         // Failed to read value
                         Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                     }})
    }}


