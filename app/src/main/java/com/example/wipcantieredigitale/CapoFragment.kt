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
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_capo.*

class CapoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListaAttivitá.setOnClickListener{
            Navigation.findNavController(view)
                .navigate(R.id.action_capoFragment_to_listaInteraCompiti)
        }
        val database = FirebaseDatabase.getInstance().getReference()
        val utentiRef = database.child("utenti")
        utentiRef.keepSynced(true) //CACHING della lettura effettuata dal database
        utentiRef.addListenerForSingleValueEvent(object : ValueEventListener {

            var lista=ArrayList<Utente?>()

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dsp in dataSnapshot.getChildren()) {
                    if(dsp.getValue(Utente::class.java)!!.ruolo=="Dipendente")
                        lista.add(dsp.getValue(Utente::class.java))
                }
                // Imposto il layout manager a lineare per avere scrolling in una direzione
                listDipendenti.layoutManager = LinearLayoutManager(activity)
                //Associo l'adapter alla RecycleView
                listDipendenti.adapter = CapoAdapter(lista, requireContext())
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

}
