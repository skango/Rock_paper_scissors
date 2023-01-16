package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class FirstFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        auth = FirebaseAuth.getInstance()

        // Find the login button in the layout
        val loginButton = view.findViewById<Button>(R.id.button)
        loginButton.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val password = view.findViewById<EditText>(R.id.editTextTextPassword).text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("FirstFragment", "signInWithEmail:success")
                        val secondFragment = SecondFragment()
                        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentContainerView, secondFragment)
                        fragmentTransaction.commit()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("FirstFragment", "signInWithEmail:failure", task.exception)
                        Toast.makeText(requireActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        return view
    }
}