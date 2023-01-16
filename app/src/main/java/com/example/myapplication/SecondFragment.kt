package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    enum class Choice {
        Rock, Paper, Scissors;
    }


    fun getUserChoice(choice: Int): Choice {
        while (true) {
            println("Please enter one of the following:")
            for ((index, item) in Choice.values().withIndex()) {
                println("${index + 1} . $item")
            }
            val userInput = readLine().toString()
            println("You have chosen $userInput")
            try {
                return Choice.valueOf(userInput)
            } catch (e: IllegalArgumentException) {
                println("Please enter a valid choice")
            }
        }
    }


    fun getComputerChoice(): Choice {
        val randomNumber = (0..2).random()
        return Choice.values()[randomNumber]
    }


    fun printResult(gameChoice: Choice, userChoice: Choice) {
        println("I have chosen $gameChoice")
        when {
            userChoice == gameChoice -> println("It is a draw")
            (userChoice == Choice.Rock && gameChoice == Choice.Scissors) ||
                    (userChoice == Choice.Scissors && gameChoice == Choice.Paper) ||
                    (userChoice == Choice.Paper && gameChoice == Choice.Rock) -> println("You win!!")
            else -> println("I win!!")
        }
    }


    fun main() {
        //val gameChoice = getComputerChoice()
       // val userChoice = getUserChoice()
        //printResult(gameChoice, userChoice)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}