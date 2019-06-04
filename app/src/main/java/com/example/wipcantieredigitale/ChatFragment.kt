package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Messaggio
import com.example.wipcantieredigitale.datamodel.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("chat")
        val myAuth=FirebaseAuth.getInstance()


                 arguments?.let {
                    val dipendente: Utente? = it.getParcelable("dipendente scelto")
                     Mittente.text=myAuth.currentUser!!.email
                     Destinatario.text=dipendente!!.mail


        btnInvia.setOnClickListener{
        val myChat=myRef.push();
                     val NewMessaggio= Messaggio(editMessaggio.text.toString(),Mittente.text.toString(),dipendente.mail )
         myChat.setValue(NewMessaggio)
            Navigation.findNavController(view).navigateUp()}


    }}}





