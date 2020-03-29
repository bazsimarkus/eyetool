package hu.bme.aut.android.eyetool

import android.app.AlertDialog
import android.app.Dialog
import android.app.FragmentManager
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result_screen.*

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.R.attr.button
import android.content.SharedPreferences
import android.widget.TextView
import kotlinx.android.synthetic.main.savedialog_layout.*
import android.preference.PreferenceManager




var creditCardWidthMm : Float = 85.60f
var creditCardHeightMm : Float = 53.98f

var pupillaryDistance : Float = 0f
var pupillaryDistanceString : String = ""
var pupillaryDistanceStringShort : String = ""

class ResultScreen : AppCompatActivity() {

    val context: Context = this
    private var button: Button? = null
    private var result: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

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






pupillaryDistance = (( creditCardWidthMm / cardDistance )* pupilsDistance )

        pupillaryDistanceString = pupillaryDistance.toString()
     //   pupillaryDistanceStringShort = String.format("%.2f", pupillaryDistanceString)
        pupillaryDistanceBox.setText(pupillaryDistanceString + " mm")

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

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        startActivity(intent)



        }

    fun shareResult(view: View) {
        var sharedString : String = "My PD is " + pupillaryDistanceString + " mm! I measured it with the EyeTool app! #eyetool #pupillarydistance #androidapp"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, sharedString)
            type = "text/plain"
        }
        startActivity(sendIntent)
         }

    fun savePdValue(view: View) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        with(sharedPref.edit()) {
            putFloat("value", pupillaryDistance)
            commit()
        }

        val storedPupillaryDistance = sharedPref.getFloat("value", -1.0f)
        val toast = Toast.makeText(
            applicationContext,
            "PD value of " + storedPupillaryDistance.toString() + " saved!",
            Toast.LENGTH_SHORT
        )
        toast.show()

    }
}