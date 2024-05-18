package com.example.datastorage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datastorage.databinding.ActivityInternalExternalStorageBinding
import java.io.File

class InternalExternalStorage : AppCompatActivity() {

    lateinit var binding: ActivityInternalExternalStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInternalExternalStorageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        saveButton()

    }

    private fun saveButton() {

        binding.saveStorage.setOnClickListener {
            val text = binding.textStorage.text.toString()
            saveTextToFile(text)
            Toast.makeText(this, "Data Saved IN Storage", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTextToFile(text: String) {
        val storagePath = applicationInfo.dataDir
        val fileName = "Sample.txt"
        val file = File("$storagePath/$fileName")
        file.writeText(text)
    }

}