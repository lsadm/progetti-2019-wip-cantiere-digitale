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
import com.example.wipcantieredigitale.datamodel.Compito
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.fragment_compiti.*
import kotlinx.android.synthetic.main.fragment_dipendente.*

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
        val compitiRef = database.getReference().child("compiti")
        var lista = ArrayList<Compito?>()
        
        compitiRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                arguments?.let {
                    val dipendente: Utente? = it.getParcelable("dipendente scelto")
                    val ruoloFlag: String? = it.getString("ruolo dipendente")

                    if (ruoloFlag == "Dipendente") {

                        var mAuth = FirebaseAuth.getInstance()

                        for (dsp in dataSnapshot.getChildren()) {
                            if (dsp.getValue(Compito::class.java)!!.dipendenteMail == mAuth!!.currentUser!!.email.toString())
                                lista.add(dsp.getValue(Compito::class.java))
                        }
                    } else {
                        dipendente?.let {
                            for (dsp in dataSnapshot.getChildren()) {
                                if (dsp.getValue(Compito::class.java)!!.dipendenteMail == it.mail)
                                    lista.add(dsp.getValue(Compito::class.java))
                            }
                        }
                    }
                }

                // Imposto il layout manager a lineare per avere scrolling in una direzione
                listCompiti.layoutManager = LinearLayoutManager(activity)
                //Associo l'adapter alla RecycleView
                listCompiti.adapter = CompitiAdapter(lista, requireContext())
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        fabAddCompito.setOnClickListener {
            arguments?.let {
                val dipendente: Utente? = it.getParcelable("dipendente scelto")
                val ruoloFlag: String? = it.getString("ruolo dipendente")

                val bundle = Bundle()
                bundle.putParcelable("dipendente scelto", dipendente)
                bundle.putString("ruolo dipendente", ruoloFlag)
                Navigation.findNavController(view)
                    .navigate(R.id.action_compitiFragment_to_aggiungiCompitoFragment, bundle)
            }
        }
    }
}
