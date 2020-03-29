package hu.bme.aut.android.eyetool

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_annotation_card.*
import kotlinx.android.synthetic.main.activity_annotation_pupils.*
import kotlinx.android.synthetic.main.activity_image_taking.*
import android.graphics.drawable.Drawable

var image_taken : Boolean = false

const val EXTRA_MESSAGE = ""

var image_uri_global: Uri? = null

class ImageTaking : AppCompatActivity() {



    private val PERMISSION_CODE = 1000;

    private val IMAGE_CAPTURE_CODE = 1001

var image_uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_taking)
//átlátszó statusbarhoz
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
// setupGoogleApiClient()
// AWSMobileClient.getInstance().initialize(this).execute()

        //átlátszó statusbar kód vége
        image_taken = false

        if( image_taken == false) {
            textView2.setText("No photo taken")
            textView3.setText("Click on 'Take Photo' to take photo")
            capture_btn.setText("Take photo")
            image_view.setImageURI(null);
            continue_btn.isEnabled = false;
        }



// első alkalommal ne az üres kép jelenjen meg, hanem egyből elindul a kamera, de az onclicklistenerben is bennevan, hogyha retakeeljük aképet, akkor a gombbal lehessen
        //  if system os is Marshmallow or above we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED
            ) {
                // permission was not enabled
                val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
// show popup to request permission
                requestPermissions(permission, PERMISSION_CODE)


            } else {
                // permission already granted
                openCamera()

            }
        } else {
            //system os is < marshmallow
            openCamera()

        }



        //retake gomb


        //button click
        capture_btn.setOnClickListener {
            //  if system os is Marshmallow or above we need to request runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    // permission was not enabled
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
// show popup to request permission
                    requestPermissions(permission, PERMISSION_CODE)


                } else {
                    // permission already granted
                    openCamera()

                }
            } else {
                //system os is < marshmallow
                openCamera()
            }


        }
    }
        private fun openCamera() {
     val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

     image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
        image_uri_global = image_uri
       }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
     // called when user presses ALLOW or DENY from permission request popup
    when(requestCode){
    PERMISSION_CODE -> {
        if (grantResults.size > 0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED){
            //permission from popup was granted

        }
else{
            // permission from popup was denied
            Toast.makeText( this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //called when image was captured from camera intent
        if(resultCode == Activity.RESULT_OK){
            image_view.setImageURI(image_uri)
            image_taken = true;
            textView2.setText("Image taking successful")
            textView3.setText("Congratulations! You successfully made a picture for the measurement. Check out below if its appropriate for you to continue, or choose 'Retake Photo' if you want to take another picture.")
            capture_btn.setText("Retake photo")
            continue_btn.isEnabled = true;
        }
    }
//átlátszósághoz
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun jumpToNextScreen(view: View) {
        val intent = Intent(this, AnnotationPupils::class.java).apply {
            putExtra(EXTRA_MESSAGE, image_uri.toString())
        }
        startActivity(intent);
        finish()
    }
    }
