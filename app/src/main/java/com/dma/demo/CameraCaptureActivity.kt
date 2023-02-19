package com.dma.demo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.FileProvider
import java.io.File
import java.net.URL

class CameraCaptureActivity : AppCompatActivity() {

    lateinit var imgCapture: AppCompatImageView
    lateinit var btnCapture: AppCompatButton

    lateinit var imageUri: Uri

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture()){
        imgCapture.setImageURI(imageUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_capture)

        imgCapture = findViewById(R.id.imgCapture)
        btnCapture = findViewById(R.id.btnCapture)

        imageUri = createImageUri()!!
        imgCapture.setOnClickListener {
            contract.launch(imageUri)
        }
    }

    private fun createImageUri(): Uri? {
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(applicationContext, "com.dma.demo.fileProvider",image)
    }
}