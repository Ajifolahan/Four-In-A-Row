//@author Momoreoluwa Ayinde

package edu.quinnipiac.ser210.fourinarowandroid

import FourInARow
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController

class PlayScreenFragment : Fragment(), View.OnClickListener {
    //instance of the FourInARow Game
    val game = FourInARow()
    //array of all the button ids from the fragment class
    val arrayofButtons = arrayOf(R.id.button1,R.id.button2, R.id.button3, R.id.button4, R.id.button5,R.id.button6,
    R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13,
        R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button19,
    R.id.button20, R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27,
        R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34,
        R.id.button35, R.id.button36)
    //instance of variables used in this program
    lateinit var win:TextView
    lateinit var turns :TextView
    lateinit var username : String
    lateinit var recipient: String
    val arrayListOfButtons = arrayListOf<ImageButton>()
    lateinit var reset: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_play_screen, container, false)
        //for loop to add our array of buttons to the arraylist of button and then
        // setOnClickListener for them
        for(i  in 0 until arrayofButtons.size){
        arrayListOfButtons.add(view.findViewById<ImageButton>(arrayofButtons[i]));
            view.findViewById<ImageButton>(arrayofButtons[i]).setOnClickListener(this);
        }
       return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initializing win and turn variables
        win= view.findViewById(R.id.textView)
        turns = view.findViewById<TextView>(R.id.turns)

        //get the username from the SplashScreenFragment
        username = PlayScreenFragmentArgs.fromBundle(requireArguments()).message
        val usernameOnScreen = view.findViewById<TextView>(R.id.username)
        //set the text of the username variable
        usernameOnScreen.text = username + "'s GAME"

        reset = view.findViewById<Button>(R.id.resetButton)
        //disable the reset button from
        reset.isEnabled = false
        //when reset button  is clicked do this
        reset.setOnClickListener {
            game.clearBoard()
            //replace all buttons to the blackSquare picture and enable the buttons
            for (i in 0 until arrayListOfButtons.size) {
                arrayListOfButtons[i].setImageResource(R.drawable.black_square)
                arrayListOfButtons[i].isEnabled = true
            }
            win.text = ""
            turns.isVisible = false
            reset.isEnabled = false
        }
    }


    override fun onClick(p0: View?) {
        var computerMove = game.computerMove
        //if a button is clicked and the id of the view and buttons are the same, then set move
        //and set the image to a red_circle
        for(i  in 0 until arrayListOfButtons.size){
            if(p0?.id  == arrayListOfButtons[i].id) {
                game.setMove(1, i);
                arrayListOfButtons[i].setImageResource(R.drawable.red_circle)
            }
        }

        //set image of the computerMove to a blue_circle
        arrayListOfButtons[computerMove].setImageResource(R.drawable.blue_circle)
        game.setMove(0, computerMove)
        turns.isVisible = true

        //from the fourInARow game if checkForWinner = 3, disable buttons, enable reset button
        // and set win.text to You Won
        if (game.checkForWinner() == 3) {
                win.text= "You Won!"
            for(i  in 0 until arrayofButtons.size){
                arrayListOfButtons[i].isEnabled = false
            }
            reset.isEnabled = true
        }  else if (game.checkForWinner() == 2){
            win.text= "Dang! Computer Won."
            for(i  in 0 until arrayofButtons.size){
                arrayListOfButtons[i].isEnabled = false
            }
            reset.isEnabled = true
        } else if (game.checkForWinner() == 1) {
            win.text= "It's a tie!"
            for(i  in 0 until arrayofButtons.size){
                arrayListOfButtons[i].isEnabled = false
            }
            reset.isEnabled = true
        }

        }
    }


