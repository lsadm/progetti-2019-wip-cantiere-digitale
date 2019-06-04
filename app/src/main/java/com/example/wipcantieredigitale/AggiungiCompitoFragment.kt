package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Compito
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_aggiungi_compito.*


class AggiungiCompitoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aggiungi_compito, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mAuth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()
        val compitiRef = database.getReference("compiti")

        btnFatto.setOnClickListener{

            if(!editNomeCompito.text.toString().equals(""))
                arguments?.let {
                    val dipendente: Utente? = it.getParcelable("dipendente scelto")
                    val ruoloFlag: String? = it.getString("ruolo dipendente")

                    if (ruoloFlag=="Dipendente") {

                        val newCompito = compitiRef.push()
                        var datiCompito = Compito(editNomeCompito.text.toString(), tvDescrizionee.text.toString(), false, mAuth!!.currentUser!!.email.toString(), newCompito.key!!)
                        newCompito.setValue(datiCompito)
                    }
                    else {
                        dipendente?.let {

                            val newCompito = compitiRef.push()
                            var datiCompito = Compito(editNomeCompito.text.toString(),tvDescrizionee.text.toString(),false, it.mail, newCompito.key!!)
                            newCompito.setValue(datiCompito)
                        }
                    }
                    Navigation.findNavController(view).navigateUp()
                }
            else
                Toast.makeText(activity, "Il compito non ha un nome", Toast.LENGTH_SHORT).show()
        }
    }
}

