//@author Momoreoluwa Ayinde

package edu.quinnipiac.ser210.fourinarowandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.invoke


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //allows for activity_main to be the activity class to be called for appearance.
       setContentView(R.layout.activity_main)
    }
}
