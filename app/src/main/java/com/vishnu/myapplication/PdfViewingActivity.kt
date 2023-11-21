package com.vishnu.myapplication

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class PdfViewingActivity : AppCompatActivity() {


    private val i = 0

    private lateinit var pdfView: WebView
    private lateinit var progress: ProgressBar

    private val removePdfTopIcon =
        "javascript:(function() {" + "document.querySelector('[role=\"toolbar\"]').remove();})()"
    private val strArray = arrayOf(
        "https://drive.google.com/u/0/uc?id=1y3dhbuVW5vqF6nL2x12AGXt5M3ZtXGtF&export=download",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewing)

        pdfView = findViewById(R.id.pdfView);
        progress = findViewById(R.id.progress);


        val pdfLink = intent.getStringExtra("pdfLink")

        if(pdfLink!=null){
            showPdfFile(pdfLink)
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun showPdfFile(imageString: String) {
        showProgress()
        pdfView.invalidate()
        pdfView.settings.javaScriptEnabled = true
        pdfView.settings.setSupportZoom(true)
        pdfView.settings.builtInZoomControls = true
        pdfView.loadUrl("http://docs.google.com/gview?embedded=true&url=$imageString")
        pdfView.webViewClient = object : WebViewClient() {
            var checkOnPageStartedCalled = false
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                checkOnPageStartedCalled = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (checkOnPageStartedCalled) {
                    pdfView.loadUrl(removePdfTopIcon)
                    hideProgress()
                } else {
                    showPdfFile(imageString)
                }
            }
        }
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

}