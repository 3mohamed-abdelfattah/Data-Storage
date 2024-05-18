package com.example.datastorage

import android.os.Bundle
import android.os.Environment
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

    // To Get Text From Input And Send it to saveTextToFile Function

    private fun saveButton() {

        binding.saveStorage.setOnClickListener {
            val text = binding.textStorage.text.toString()
            saveTextToFile(text)
            Toast.makeText(this, "Data Saved IN Storage", Toast.LENGTH_SHORT).show()
        }
    }

    // TO Save Text In Internal Storage  (/data/data/com.example.datastorage/Sample.txt)

//    private fun saveTextToFile(text: String) {
//        val storagePath = applicationInfo.dataDir
//        val fileName = "Sample.txt"
//        val file = File("$storagePath/$fileName")
//        file.writeText(text)
//    }


    // TO Save Text In External Storage (/sdcard/Android/data/com.example.datastorage/files/Sample.txt)

//    private fun saveTextToFile(text: String) {
//        val storagePath = getExternalFilesDir(null)?.path.toString()
//        val fileName = "Sample.txt"
//        val file = File("$storagePath/$fileName")
//        file.writeText(text)
//    }


    // TO Save Text In External Storage ( IN Main External ) Need Permission !

    private fun saveTextToFile(text: String) {
        val storagePath = Environment.getExternalStorageDirectory().path
        val fileName = "Sample.txt"
        val file = File("$storagePath/$fileName")
        file.writeText(text)
    }

}