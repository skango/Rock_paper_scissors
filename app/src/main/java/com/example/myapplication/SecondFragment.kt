package com.example.myapplication

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

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

    var playerchoice: Choice? = null
    var botchoice: Choice? = null

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


    fun printResult(gameChoice: Choice, userChoice: Choice) : String{
        println("I have chosen $gameChoice")
        when {
            userChoice == gameChoice -> return  ("Draw")
            (userChoice == Choice.Rock && gameChoice == Choice.Scissors) ||
                    (userChoice == Choice.Scissors && gameChoice == Choice.Paper) ||
                    (userChoice == Choice.Paper && gameChoice == Choice.Rock) -> return ("You Loose!")
            else -> return "You Win"
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

    fun ComputerMove() {
        var compchoice = getComputerChoice()
        botchoice = compchoice
        view?.findViewById<ImageView>(R.id.imageView5)?.visibility = View.VISIBLE
        when (compchoice) {
            Choice.Rock -> view?.findViewById<ImageView>(R.id.imageView5)?.setImageResource(R.drawable.rock)
            Choice.Paper -> view?.findViewById<ImageView>(R.id.imageView5)?.setImageResource(R.drawable.paper)
            Choice.Scissors -> view?.findViewById<ImageView>(R.id.imageView5)?.setImageResource(R.drawable.scissors)
        }
    }

    override fun onStart() {
        super.onStart()
        if (!baseData.isLoggedIn!!)
        {
            Toast.makeText(context, "You are not logged in!", Toast.LENGTH_SHORT).show()
        }
        view?.findViewById<ImageView>(R.id.imageView4)?.visibility  = View.GONE
        view?.findViewById<ImageView>(R.id.imageView5)?.visibility  = View.GONE
        view?.findViewById<TextView>(R.id.textView3)?.text  = "Start The game"

        view?.findViewById<ImageView>(R.id.imageView)?.setOnClickListener {
            if (baseData.isLoggedIn!!) {
                playerchoice = Choice.Rock
                view?.findViewById<ImageView>(R.id.imageView4)?.visibility = View.VISIBLE
                view?.findViewById<ImageView>(R.id.imageView4)?.setImageResource(R.drawable.rock)
                ComputerMove()
                var status = botchoice?.let { it1 -> printResult(playerchoice!!, it1) }
                view?.findViewById<TextView>(R.id.textView3)?.text  = status
            }
        }

        view?.findViewById<ImageView>(R.id.imageView2)?.setOnClickListener {
            if (baseData.isLoggedIn!!) {
                playerchoice = Choice.Paper
                view?.findViewById<ImageView>(R.id.imageView4)?.visibility = View.VISIBLE
                view?.findViewById<ImageView>(R.id.imageView4)?.setImageResource(R.drawable.paper)
                ComputerMove()
                var status = botchoice?.let { it1 -> printResult(playerchoice!!, it1) }
                view?.findViewById<TextView>(R.id.textView3)?.text  = status
            }
        }
        view?.findViewById<ImageView>(R.id.imageView3)?.setOnClickListener {
            if (baseData.isLoggedIn!!) {
                playerchoice = Choice.Scissors
                view?.findViewById<ImageView>(R.id.imageView4)?.visibility = View.VISIBLE
                view?.findViewById<ImageView>(R.id.imageView4)
                    ?.setImageResource(R.drawable.scissors)
                ComputerMove()
                var status = botchoice?.let { it1 -> printResult(playerchoice!!, it1) }
                view?.findViewById<TextView>(R.id.textView3)?.text  = status
            }
        }
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