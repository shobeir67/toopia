package com.shobeir.toopia.ui.screen.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewHolder(context: Context) {

    var webView:WebView = WebView(context)

    private var webChromeClient = object :WebChromeClient(){
        override fun onCreateWindow(
            view: WebView?,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }

        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            return super.onConsoleMessage(consoleMessage)
        }
    }

    private var webViewClient = object :WebViewClient(){
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
        }
        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    init {
        webView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSetting= webView.settings

        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webSetting.userAgentString = System.getProperty("http.agent")
        webSetting.useWideViewPort=true
        webSetting.loadWithOverviewMode=true
        webSetting.allowFileAccess=true
        webSetting.allowContentAccess=true
        webSetting.setSupportZoom(true)
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        webSetting.builtInZoomControls=true
        webSetting.displayZoomControls=false
        webSetting.javaScriptCanOpenWindowsAutomatically=true
        webSetting.loadsImagesAutomatically=true
        WebView.setWebContentsDebuggingEnabled(true)

        setupWebChromeClient()
        setupWebViewClient()

    }

    private fun setupWebChromeClient(){
        webView.webChromeClient=webChromeClient
    }

    private fun setupWebViewClient(){
        webView.webViewClient=webViewClient
    }

    fun loadUrl(url:String){
        webView.loadUrl(url)
    }

    fun reload(){
        webView.reload()
    }
}