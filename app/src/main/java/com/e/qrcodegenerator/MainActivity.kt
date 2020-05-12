package com.e.qrcodegenerator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scan.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setPrompt("Scan a barcode or QR code")
            integrator.setOrientationLocked(true)
            integrator.initiateScan()
        }

        generate.setOnClickListener {
            val dialog = TextDialog(this)
            dialog.onGenerateClicked = object : TextDialog.OnGenerateClicked {
                override fun generate(str: String) {
                    val intent = Intent(this@MainActivity, Main2Activity::class.java)
                    intent.putExtra("str", str)
                    startActivity(intent)
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {

            if (result.contents == null)
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Result...")
                dialog.setMessage(result.contents)
                dialog.setPositiveButton("OK") { d, _ ->
                    d.cancel()
                }
                dialog.setCancelable(false)
                dialog.create().show()
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

