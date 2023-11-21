package com.vishnu.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vishnu.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.loadPdf.setOnClickListener {
            val intent = Intent(this,PdfViewingActivity::class.java)
            intent.putExtra("pdfLink","https://drive.google.com/u/0/uc?id=1tJPv8QvCvDBAPYxHvLSseHAUeRqu8HiP&export=download")
            startActivity(intent)

        }
    }
}
