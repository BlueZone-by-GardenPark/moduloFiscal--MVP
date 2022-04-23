package br.com.zonaazul.projetofiscal

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import br.com.zonaazul.projetofiscal.databinding.ActivityRegistroIrregBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.IOException

class RegistroIrregActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityRegistroIrregBinding
    //private lateinit var btnBack: MaterialButton
    private lateinit var btnOpenCamera: MaterialButton
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var imageView4: ImageView

    var REQUEST_TAKE_PHOTO = 1

    lateinit var photoPath: String
    lateinit var photoPath2: String
    lateinit var photoPath3: String
    lateinit var photoPath4: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_irreg)

        btnOpenCamera = findViewById(R.id.btnOpenCamera)
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        imageView4 = findViewById(R.id.imageView4)

        btnOpenCamera.setOnClickListener{
            takePicture()
            takePicture2()
            takePicture3()
            takePicture4()
        }
    }

   private fun takePicture(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager)!= null){
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            }catch (e: IOException){}
             if (photoFile != null){

                val photoUri = FileProvider.getUriForFile(
                    this,
                    "br.com.zonaazul.projetofiscal",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    private fun takePicture2(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager)!= null){
            var photoFile: File? = null
            try {
                photoFile = createImageFile2()
            }catch (e: IOException){}
            if (photoFile != null){

                val photoUri = FileProvider.getUriForFile(
                    this,
                    "br.com.zonaazul.projetofiscal",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    private fun takePicture3(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager)!= null){
            var photoFile: File? = null
            try {
                photoFile = createImageFile3()
            }catch (e: IOException){}
            if (photoFile != null){

                val photoUri = FileProvider.getUriForFile(
                    this,
                    "br.com.zonaazul.projetofiscal",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    private fun takePicture4(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager)!= null){
            var photoFile: File? = null
            try {
                photoFile = createImageFile4()
            }catch (e: IOException){}
            if (photoFile != null){

                val photoUri = FileProvider.getUriForFile(
                    this,
                    "br.com.zonaazul.projetofiscal",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK){
            imageView1.setImageURI(Uri.parse(photoPath))
            imageView2.setImageURI(Uri.parse(photoPath2))
            imageView3.setImageURI(Uri.parse(photoPath3))
            imageView4.setImageURI(Uri.parse(photoPath4))
        }
    }

    private fun createImageFile(): File? {
        val fileName = "Photo"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )
        photoPath = image.absolutePath
        return image
    }

    private fun createImageFile2(): File? {
        val fileName = "Photo"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )
        photoPath2 = image.absolutePath
        return image
    }

    private fun createImageFile3(): File? {
        val fileName = "Photo"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )
        photoPath3 = image.absolutePath
        return image
    }

    private fun createImageFile4(): File? {
        val fileName = "Photo"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )
        photoPath4 = image.absolutePath
        return image
    }
}