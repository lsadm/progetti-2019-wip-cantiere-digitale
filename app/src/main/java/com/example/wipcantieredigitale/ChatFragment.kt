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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mAuth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        var lista = ArrayList<Messaggio?>()
        /////////////////////////////////////////////////////////////////////////
        //Recupero il numero di cellulare dell'utente corrente
        val cellulareRef = database.getReference().child("utenti")
            .child(mAuth.currentUser!!.uid).child("cellulare")
        var cellulare = ""

        cellulareRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                cellulare = dataSnapshot.getValue().toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })//////////////////////////////////////////////////////////////////////
        val cellulareChatRef = database.getReference().child("chat").child(cellulare)

        //Lettura messaggi
        cellulareChatRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                arguments?.let {

                    val dipendente: Utente? = it.getParcelable("dipendente scelto")
                    dipendente?.let {

                        for (dsp in dataSnapshot.getChildren()) {
                            if (dsp.getValue(Messaggio::class.java)!!.mittente == it.cellulare) {
                                lista.add(dsp.getValue(Messaggio::class.java))
                            }
                        }
                    }
                }
                // Imposto il layout manager a lineare per avere scrolling in una direzione
                listMessaggi.layoutManager = LinearLayoutManager(activity)
                //Associo l'adapter alla RecycleView
                listMessaggi.adapter = MessaggiAdapter(lista, requireContext())
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        btnInvia.setOnClickListener {

            arguments?.let {

                val dipendente: Utente? = it.getParcelable("dipendente scelto")
                dipendente?.let {
                    val newMessage = cellulareChatRef.push()
                    val datiMessagge = Messaggio(it.cellulare, editMessaggio.text.toString())
                    newMessage.setValue(datiMessagge)
                }
            }
            MessaggiAdapter(lista, requireContext()).notifyDataSetChanged()
        }
    }
}