package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Compito
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_compito.*
import kotlinx.android.synthetic.main.fragment_compito.tvDescrizionee
import kotlinx.android.synthetic.main.fragment_compito.btnFatto

class CompitoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compito, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        val mAuth=FirebaseAuth.getInstance()
         arguments?.let {

            val compito: Compito? = it.getParcelable("compito scelto")
            compito?.let {
                tvNomeCompito.text = it.nome
                tvDescrizionee.text = it.descrizione
                Incaricato.text=it.dipendenteMail
            }
             if(compito!!.dipendenteMail==mAuth!!.currentUser!!.email)
             {tvIncaricato.setVisibility(View.GONE)
                 Incaricato.setVisibility(View.GONE)


                 if (compito.done == true)
                btnFatto.setVisibility(View.GONE)}
                 if(compito.approvato)
                 btnFatto.setVisibility(View.GONE)
            btnFatto.setOnClickListener {



                 val compitoRef = database.getReference().child("compiti").child(compito.codCompito)
                compitoRef.child("done").setValue(true)
                 if(compito.dipendenteMail!= mAuth.currentUser!!.email)
                compitoRef.child("approvato").setValue(true)
                Navigation.findNavController(view).navigateUp()
            }
        }
    }
}
