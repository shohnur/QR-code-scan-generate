package com.e.qrcodegenerator

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText

class TextDialog(context: Context) : AlertDialog(context) {

    private var editText: EditText? = null
    private var generate: Button? = null
    private var cancel: Button? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dialog, null, false)
        editText = view.findViewById(R.id.matn)
        generate = view.findViewById(R.id.generate)
        cancel = view.findViewById(R.id.cancel)
        setTitle("QR code generator")
        this.cancel!!.setOnClickListener {
            cancel()
        }
        this.generate!!.setOnClickListener {
            if (this.editText!!.text != null && this.editText!!.text.toString() != "") {
                onGenerateClicked?.generate(this.editText!!.text.toString())
                cancel()
            } else {
                this.editText!!.error = "Enter text..."
            }
        }
        setCancelable(false)
        setView(view)
        create()
        show()

    }

    interface OnGenerateClicked {
        fun generate(str: String)
    }

    var onGenerateClicked: OnGenerateClicked? = null

}