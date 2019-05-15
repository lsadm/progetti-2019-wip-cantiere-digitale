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



        /////////////////////////////////////
        /////////////////////////////////////
        val databaseChat = FirebaseDatabase.getInstance()
        val myRef = databaseChat.getReference()
        val chatRef = myRef.child("chat")

        ////////////////////////////////////////////////////////////
            button2.setOnClickListener {
                if(!editUsername.text.toString().equals("")){
                    val database = FirebaseDatabase.getInstance()
                    /*      val auth=FirebaseAuth.getInstance()
                          auth.createUserWithEmailAndPassword(idpass.text.toString(),editp.text.toString())*/

                    var uti=true
                    val myRef = database.getReference(editUsername.text.toString() )
                    myRef.addValueEventListener(object : ValueEventListener {

                        override fun onCancelled(error: DatabaseError) {
                        }
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            if(dataSnapshot.exists()) {
                                if (uti){ Toast.makeText(activity, "esiste giá", Toast.LENGTH_SHORT).show()
                                }

                            }
                            else
                            {
                                val dateFormatter = SimpleDateFormat("dd/MM/yyyy hh.mm.ss")
                                dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                                dateFormatter.isLenient = false
                                val today = Date()
                                val s = dateFormatter.format(today)


                                var nameList= login(editUsername.text.toString(),editp.text.toString(),idpass.text.toString(),editNome.text.toString(),editCognome.text.toString(),mySpinner.getSelectedItem().toString(),s)
                                myRef.setValue(nameList)
                                myRef.child("compiti").push()
                                uti=false;

                                Navigation.findNavController(view).navigateUp()
                                fragmentManager?.popBackStack()
                                ;
                            }
                        }
                    })
                }
            }
        ////////////////////////////////////////////////////////////
        chatRef.addListenerForSingleValueEvent(object : ValueEventListener {
            var lista=ArrayList<String?>()

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dsp in dataSnapshot.getChildren()) {
                        lista.add(dsp.getValue(String::class.java)) //add result into array list
                }
                listMessaggi.layoutManager = LinearLayoutManager(activity)
                listMessaggi.adapter = ChatAdapter(lista, requireContext())
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
}

