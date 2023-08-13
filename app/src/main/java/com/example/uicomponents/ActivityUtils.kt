package com.example.uicomponents

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CustomDialog {
    companion object {
        fun showDialog(context: Context, callback: () -> Unit) {
            val dialog = Dialog(context)
            dialog.apply {
                setContentView(R.layout.del_dialog)
                setCancelable(false)
//              window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
fun showCustomToast(context: Context, message: String? = null){
    val layout = LayoutInflater.from(context).inflate(R.layout.custom_toast,null)
    val text = layout.findViewById<TextView>(R.id.custom_toast_text)
    text.text = message
    Toast(context).apply {
        duration = Toast.LENGTH_SHORT
        view = layout
        show()
    }
}
