package com.example.datastorage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datastorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = binding.username
        val password = binding.password


        // To get UserName and Password Automatically When Open Application Again

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("myData", Context.MODE_PRIVATE)
        val getUserName = sharedPreferences.getString("username", "None")
        val getPassword = sharedPreferences.getString("password", "")
        user.setText(getUserName)
        password.setText(getPassword)

        makeText(
            this,
            "Your User: $getUserName Password: $getPassword",
            Toast.LENGTH_LONG
        ).show()


        // To Set UserName and Password from Input Parameters and save them

        binding.login.setOnClickListener {
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("myData", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", user.text.toString())
            editor.putString("password", password.text.toString())
            editor.apply()

            makeText(this, "DataSaved", Toast.LENGTH_SHORT).show()
        }

        // To Get UserName and Password after close Application by pressing Load Data button

//        binding.loadData.setOnClickListener {
//            val sharedPreferences: SharedPreferences =
//                getSharedPreferences("myData", Context.MODE_PRIVATE)
//            val getUserName = sharedPreferences.getString("username", "None")
//            val getPassword = sharedPreferences.getString("password", "")
//            user.setText(getUserName)
//            password.setText(getPassword)
//
//            makeText(
//                this,
//                "Your User: $getUserName Password: $getPassword",
//                Toast.LENGTH_LONG
//            ).show()
//        }


    }
}