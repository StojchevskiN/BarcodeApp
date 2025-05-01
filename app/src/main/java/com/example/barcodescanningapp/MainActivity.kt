package com.example.barcodescanningapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scan_btn = findViewById<Button>(R.id.scan_btn)
        scan_btn.setOnClickListener { v -> scanCode() }
    }

    private fun scanCode() {
        val options = ScanOptions()
        options.setPrompt("Volume up to flash on")
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        options.captureActivity = CaptureAct::class.java
        barLaucher.launch(options)

    }

    var barLaucher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->

        if (result.contents != null) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Result")
            builder.setMessage(result.contents)

            builder.setPositiveButton("OK") {
                    dialogInterface, i -> dialogInterface.dismiss()
            }
                .show()
        }
    }


}