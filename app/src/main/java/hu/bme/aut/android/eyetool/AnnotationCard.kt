package hu.bme.aut.android.eyetool

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_annotation_card.*
import kotlinx.android.synthetic.main.activity_annotation_pupils.*
import kotlin.math.abs
import kotlin.math.sqrt

var cardDistance : Float = 0f

class AnnotationCard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation_card)


      //  textViewCard.setText(image_uri_global.toString())
        imageViewCard.setImageURI(image_uri_global)


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

        var listener1 = View.OnTouchListener(function = {view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }

            true

        })

        // Declared in our activity_shapes_view.xml file.
        markerCard1.setOnTouchListener(listener1)

        var listener2 = View.OnTouchListener(function = {view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }

            true

        })

        // Declared in our activity_shapes_view.xml file.
        markerCard2.setOnTouchListener(listener2)


    }


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
        cardDistance = sqrt(abs((markerCard2.x-markerCard1.x)*(markerCard2.x-markerCard1.x)+(markerCard2.y-markerCard1.y)*(markerCard2.y-markerCard1.y)))

        val intent = Intent(this, ResultScreen::class.java).apply {
          //  putExtra(EXTRA_MESSAGE, image_uri.toString())
        }
        startActivity(intent);
        finish()
    }
}