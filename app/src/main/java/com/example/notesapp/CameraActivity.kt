package com.example.notesapp


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import android.media.MediaScannerConnection
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 100
    private lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)  // make sure this XML exists

        val cameraBtn = findViewById<Button>(R.id.btnCapture)
        cameraBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 101)
            } else {
                takePicture()
            }

        }
    }


    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile = createImageFile()

        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            photoFile
        )

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        startActivityForResult(intent, CAMERA_REQUEST)
    }


//    private fun createImageFile(): File {
//        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        val fileName = "IMG_$timeStamp"
// //   val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//     val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//
//
//        val image = File.createTempFile(fileName, ".jpg", storageDir)
//
//        MediaScannerConnection.scanFile(
//            this,
//            arrayOf(image.absolutePath),
//            arrayOf("image/jpeg")
//        ) { path, uri ->
//            Log.d("Camera", "Image scanned at path: $path, uri: $uri")
//        }
//
//        return image
//    }


    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timeStamp"

        val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera")

        // Make sure the directory exists
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }

        val image = File.createTempFile(fileName, ".jpg", storageDir)

        // MediaScanner to make it visible in gallery
        MediaScannerConnection.scanFile(
            this,
            arrayOf(image.absolutePath),
            arrayOf("image/jpeg")
        ) { path, uri ->
            Log.d("Camera", "Image scanned at path: $path, uri: $uri")
        }

        return image
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
//            Toast.makeText(this, "Photo saved!", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Toast.makeText(this, "Photo saved!", Toast.LENGTH_SHORT).show()

            // 👇 Open the saved image in any gallery/image viewer app
            val viewIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(photoUri, "image/*")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            startActivity(viewIntent)
        }
    }

}