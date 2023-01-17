package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirstFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    //false = registracia, true = shesvla
    var mshia = true
    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        var defaultvisibility = view.findViewById<EditText>(R.id.editTextTextPersonName).visibility
        var defaultbtntext = view.findViewById<Button>(R.id.button).text
        var defaultloginbtntext = view.findViewById<Button>(R.id.loginbtn).text

        val switcherbtn = view?.findViewById<Button>(R.id.loginbtn)?.setOnClickListener {
            if (mshia) {
                view.findViewById<EditText>(R.id.editTextTextPersonName).visibility = View.GONE
                view.findViewById<Button>(R.id.button).text = "Login"
                view.findViewById<Button>(R.id.loginbtn).text = "No account? Register"


            }else {
                view.findViewById<EditText>(R.id.editTextTextPersonName).visibility = defaultvisibility
                view.findViewById<Button>(R.id.button).text = defaultbtntext
                view.findViewById<Button>(R.id.loginbtn).text = defaultloginbtntext
            }
            mshia = !mshia
        }

        auth = FirebaseAuth.getInstance()

        // Find the login button in the layout
        val loginButton = view.findViewById<Button>(R.id.button)
        loginButton.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val password = view.findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            if (!mshia) {
                try {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                baseData.isLoggedIn = true
                                // Sign in success, update UI with the signed-in user's information
                               /* var id = auth.currentUser?.uid;
                                var username =
                                    view.findViewById<EditText>(R.id.editTextTextPersonName).text;
                                // Write a message to the database
                                val database = Firebase.database
                                if (id != null) {
                                    database.getReference(id).child("name")
                                        .setValue(username.toString())
                                }
*/
                                Toast.makeText(context, "You are logged in!", Toast.LENGTH_SHORT).show()
                                //Log.d("FirstFragment", "signInWithEmail:success")
                                /*val secondFragment = SecondFragment()
                                val fragmentTransaction =
                                    requireActivity().supportFragmentManager.beginTransaction()
                                fragmentTransaction.replace(
                                    R.id.fragmentContainerView,
                                    secondFragment
                                )
                                fragmentTransaction.commit()*/
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w("FirstFragment", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    requireActivity(),
                                    "Authentication failed " + (task.exception?.message ?: ""),
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                } catch (e: Exception) {
                    println(e)
                    Toast.makeText(
                        requireActivity(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }else {
                try {
                    var valid = view.findViewById<EditText>(R.id.editTextTextPersonName).text.isNullOrBlank()
                    if (valid) {
                        Toast.makeText(
                            requireActivity(),
                            "Name Can not be empty!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else {

                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    baseData.isLoggedIn = true
                                    // Sign in success, update UI with the signed-in user's information
                                    var id = auth.currentUser?.uid;
                                    var username =
                                        view.findViewById<EditText>(R.id.editTextTextPersonName).text;
                                    // Write a message to the database
                                    val database = Firebase.database
                                    if (id != null) {
                                        database.getReference(id).child("name")
                                            .setValue(username.toString())
                                    }

                                    Toast.makeText(context, "You are logged in!", Toast.LENGTH_SHORT).show()
                                    //Log.d("FirstFragment", "signInWithEmail:success")
                                   /* val secondFragment = SecondFragment()
                                    val fragmentTransaction =
                                        requireActivity().supportFragmentManager.beginTransaction()
                                    fragmentTransaction.replace(
                                        R.id.fragmentContainerView,
                                        secondFragment
                                    )
                                    fragmentTransaction.commit()*/
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w("FirstFragment", "signInWithEmail:failure", task.exception)
                                    Toast.makeText(
                                        requireActivity(),
                                        "Authentication failed " + (task.exception?.message ?: ""),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                    }
                } catch (e: Exception) {
                    println(e)
                    Toast.makeText(
                        requireActivity(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }

        return view
    }
}