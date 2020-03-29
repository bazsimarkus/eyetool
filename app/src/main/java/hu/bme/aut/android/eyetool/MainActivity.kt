package hu.bme.aut.android.eyetool

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.savedialog_layout.*
import android.widget.TextView
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.preference.PreferenceManager




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
       // setupGoogleApiClient()
       // AWSMobileClient.getInstance().initialize(this).execute()
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
        val intent = Intent(this, Tutorial1::class.java).apply {  }
        startActivity(intent);
    }

    fun showResults(view: View) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val storedPupillaryDistance = sharedPref.getFloat("value", 0.0f)
        if(storedPupillaryDistance == 0.0f){
            val toast = Toast.makeText(applicationContext, "You have not saved a PD value yet!", Toast.LENGTH_SHORT)
            toast.show()
        }
        else{
            val toast = Toast.makeText(applicationContext, "Your saved PD is: " + storedPupillaryDistance.toString() + " mm", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    fun clickedOnLogo(view: View) {
        val toast = Toast.makeText(applicationContext, "Android alapú szoftverfejlesztés - Házi feladat - 2018 - Írta: Márkus Balázs - BME Automatizálási és Alkalmazott Informatikai Tanszék", Toast.LENGTH_SHORT)
        toast.show()
    }


}
