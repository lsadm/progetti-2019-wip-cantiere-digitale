package com.example.wipcantieredigitale


import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Messaggio
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_aggiungi_compito.*
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    var mAuth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()
    var lista = ArrayList<Messaggio?>()
    private lateinit var adapter: MessaggiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = database.getReference()
        val cellulareRef = database.child("utenti").child(mAuth!!.currentUser!!.uid).child("cellulare")
        var mioCellulare = "vuoto"

        cellulareRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                mioCellulare = dataSnapshot.getValue(String::class.java)!!
                val cellulareChatRef = database.child("chat").child(mioCellulare)

                //LETTURA E SCRITTURA MESSAGGI DA PARTE DEL CAPO
                arguments?.let {
                    val dipendente: Utente? = it.getParcelable("dipendente scelto")

                    if (dipendente==dipendente) {
                    cellulareChatRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            dipendente?.let {

                                for (dsp in dataSnapshot.getChildren()) {
                                    if (dsp.getValue(Messaggio::class.java)!!.destinatario == mioCellulare
                                        || dsp.getValue(Messaggio::class.java)!!.mittente == it.cellulare
                                    ) {
                                        lista.add(dsp.getValue(Messaggio::class.java))
                                    }
                                }
                            }
                            // Imposto il layout manager a lineare per avere scrolling in una direzione
                            listMessaggi.layoutManager = LinearLayoutManager(activity)
                            //Associo l'adapter alla RecycleView
                            adapter = MessaggiAdapter(lista, requireContext())
                            listMessaggi.adapter = adapter
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Failed to read value
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })

                    btnInvia.setOnClickListener {

                        if (editMessaggio.text.toString() != "")
                            arguments?.let {

                                val dipendente: Utente? = it.getParcelable("dipendente scelto")
                                dipendente?.let {

                                    val newMessage = cellulareChatRef.push()
                                    val datiMessagge =
                                        Messaggio(editMessaggio.text.toString(), mioCellulare, it.cellulare)
                                    newMessage.setValue(datiMessagge)
                                }
                            }
                        else {
                            Toast.makeText(context, "Scrivi qualcosa", Toast.LENGTH_SHORT).show()
                        }
                        editMessaggio.text = null
                        adapter.notifyDataSetChanged()
                    }}
                }
                ////////////////////////////////////////////////////////////
                //LETTURA E SCRITTURA MESSAGGI DA PARTE DEL DIPENDENTE
                arguments?.let {
                    val ruoloFlag: String? = it.getString("ruolo dipendente")


                    if (ruoloFlag==ruoloFlag){

                    var capoCellulare =""

                    cellulareChatRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            for (dsp in dataSnapshot.getChildren()) {
                                if (dsp.getValue(Messaggio::class.java)!!.destinatario == mioCellulare
                                    || dsp.getValue(Messaggio::class.java)!!.mittente == mioCellulare)
                                {
                                    lista.add(dsp.getValue(Messaggio::class.java))
                                    if (dsp.getValue(Messaggio::class.java)!!.destinatario == mioCellulare)
                                        capoCellulare = dsp.getValue(Messaggio::class.java)!!.mittente

                                }
                            }
                            // Imposto il layout manager a lineare per avere scrolling in una direzione
                            listMessaggi.layoutManager = LinearLayoutManager(activity)
                            //Associo l'adapter alla RecycleView
                            adapter = MessaggiAdapter(lista, requireContext())
                            listMessaggi.adapter = adapter
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Failed to read value
                            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                        }
                    })

                    btnInvia.setOnClickListener {

                        if (editMessaggio.text.toString() != "") {

                            val newMessage = cellulareChatRef.push()
                            val datiMessagge = Messaggio(editMessaggio.text.toString(), mioCellulare, capoCellulare)
                            newMessage.setValue(datiMessagge)
                        }
                        else {
                            Toast.makeText(context, "Scrivi qualcosa", Toast.LENGTH_SHORT).show()
                        }
                        editMessaggio.text = null
                        adapter.notifyDataSetChanged()
                    }}
                }
                ////////////////////////////////////////////////////////////
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
}