package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registrazione.*
import java.util.*
 import kotlin.collections.ArrayList




class RegistrazioneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registrazione, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button2.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference(editUsername.text.toString())
            var nameList: ArrayList<String> = arrayListOf<String>(editPassword.text.toString(),editPassword3.text.toString(),spinner.getSelectedItem().toString())
            myRef.setValue(nameList)
            Navigation.findNavController(it).navigate(R.id.action_registrazioneFragment_to_loginFragment)
        }

    }}
//