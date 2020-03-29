package hu.bme.aut.android.eyetool

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle

class FireMissilesDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Save result?")
                    .setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialog, id ->
                                // FIRE ZE MISSILES!
                            })
                    .setNegativeButton("Cancel",
                            DialogInterface.OnClickListener { dialog, id ->
                                // User cancelled the dialog
                            })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}