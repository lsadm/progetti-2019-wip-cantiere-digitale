package com.example.wipcantieredigitale


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Chat
import com.example.wipcantieredigitale.datamodel.login
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_lavoratore.*
import kotlinx.android.synthetic.main.fragment_registrazione.*
import kotlinx.android.synthetic.main.riga_chat.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
        val mittente=(activity as MainActivity).getL()

        //Recupero username lavoratore
        arguments?.let {
            val prova: login? = it.getParcelable("scelta")
            prova?.let {
                nomeDipendente.text = it.username

                val destinatario = it.username

                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference()

                //INSERIMENTO MESSAGGI
                btnInvia.setOnClickListener {

                    //if(!editMessage.text.toString().equals("")){   //Se il campo non è scrittura non è vuoto:

                        val chatRef = database.getReference("chat")
                        val myMessagesRef = chatRef.child(destinatario)

                        myMessagesRef.addListenerForSingleValueEvent(object : ValueEventListener {

                            override fun onDataChange(dataSnapshot: DataSnapshot) {

                                    var nameList= Chat(mittente!!.username, editMessage.text.toString())
                                    myMessagesRef.push().setValue(nameList)

                                    Navigation.findNavController(view).navigateUp()

                            }
                            override fun onCancelled(error: DatabaseError) {
                            }
                        })
                    //}
                }
            }
        }
    }
}

