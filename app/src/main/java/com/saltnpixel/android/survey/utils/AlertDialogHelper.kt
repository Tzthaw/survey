package com.saltnpixel.android.survey.utils

import android.app.Activity
import com.saltnpixel.android.survey.R
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*

object AlertDialogHelper {
    fun displayAlert(activity: Activity,errorMessage:String) {
        val timer = object : Thread() {
            override fun run() {
                activity.runOnUiThread {
                    val builder = android.app.AlertDialog.Builder(activity)
                    val dialogView = activity.layoutInflater.inflate(R.layout.custom_alert_dialog, null)
                    builder.setView(dialogView)
                    val tv = dialogView.txt_dia
                    tv.text =errorMessage
                    val one = dialogView.btn_dismiss
                    val dialog = builder.create()
                    one.setOnClickListener {
                        // TODO Auto-generated method stub
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            }
        }
        timer.run()
    }
}
