import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.CompitiAdapter
import com.example.wipcantieredigitale.R
import com.example.wipcantieredigitale.datamodel.Compito

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_capo.*

import kotlinx.android.synthetic.main.fragment_compiti.*
import kotlinx.android.synthetic.main.fragment_compiti.listCompiti
import kotlinx.android.synthetic.main.fragment_lista_intera_compiti.*

class ListaInteraCompitiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_intera_compiti, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    val database = FirebaseDatabase.getInstance()
        ListaDipendenti.setOnClickListener{
            Navigation.findNavController(view)
                .navigateUp()}
    val compitiRef = database.getReference().child("compiti")
        compitiRef.keepSynced(true) //caching
    var lista = ArrayList<Compito?>()
    compitiRef.addListenerForSingleValueEvent(object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (dsp in dataSnapshot.getChildren()) {
                            lista.add(dsp.getValue(Compito::class.java))}
             listCompiti.layoutManager = LinearLayoutManager(activity)
             listCompiti.adapter = CompitiAdapter(lista, requireContext())
        }

        override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
        }
    })
    }}
