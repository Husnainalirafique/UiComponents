package com.example.uicomponents

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button

class CustomDialog {
    companion object {
        fun showDialog(context: Context, message: String? = null, callback: () -> Unit) {
            val dialog = Dialog(context)
            dialog.apply {
                setContentView(R.layout.del_dialog)
                setCancelable(false)
//                        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                findViewById<Button>(R.id.confirmButton).setOnClickListener {
                    callback.invoke()
                    dismiss()
                }
                findViewById<Button>(R.id.cancelButton).setOnClickListener {
                    cancel()
                }
                show()
            }
        }
    }
}