package com.shobeir.toopia.ui.screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(
    url: String
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {context->
                WebViewHolder(context).apply {
                    loadUrl(url)
                }.webView
        }, update = {
            it.loadUrl(url)
     })
}