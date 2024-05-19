package com.example.datastorage

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datastorage.databinding.ActivityInternalExternalStorageBinding
import java.io.File
import android.Manifest


class InternalExternalStorage : AppCompatActivity() {

    lateinit var binding: ActivityInternalExternalStorageBinding

    //Request runtime permissions

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {

            } else {

            }
        }

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

            //        Request runtime permissions
            // TO View Permissions On Screen And User Choice
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                saveTextToFile(text)
                Toast.makeText(this, "Data Saved IN Storage", Toast.LENGTH_SHORT).show()
            } else {
                requestPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }

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