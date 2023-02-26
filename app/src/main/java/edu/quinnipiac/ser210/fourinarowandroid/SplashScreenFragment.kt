//@author Momoreoluwa Ayinde

package edu.quinnipiac.ser210.fourinarowandroid

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController

class SplashScreenFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        //uses the root view to get a reference to the fragment's start button
        val startButton = view.findViewById<Button>(R.id.start)
        val welcome = view.findViewById<EditText>(R.id.PersonName)
        //on click, do this.
        startButton.setOnClickListener {
            val navController = view.findNavController()
            val message = welcome.text.toString()
            //navigates to the action ID
            if(message.equals("")){
                //if the username text is empty, show a toast
                Toast.makeText(context, "Please enter a valid name to proceed", Toast.LENGTH_LONG).show()
            } else {
                val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToPlayScreenFragment(message)
                navController.navigate(action)
            }

        }
        return view
    }

}