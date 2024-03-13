package com.cookiegames.smartcookie.core.usecase

import android.content.Context
import android.webkit.WebView
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetWebViewVersionName @Inject constructor(
  @ApplicationContext private val context: Context
) {
  private fun extractWebViewVersion(userAgent: String): String? {
    val parts = userAgent.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
      .toTypedArray()
    for (part in parts) {
      if (part.startsWith("Chrome/")) {
        val chromeParts = part.split("/".toRegex()).dropLastWhile { it.isEmpty() }
          .toTypedArray()
        if (chromeParts.size >= 2) {
          return chromeParts[1]
        }
      }
    }
    return null
  }

  operator fun invoke(): String {
    // get system webview version
    val webView = WebView(context)
    val webViewVersion = webView.settings.userAgentString
    // Extract the WebView version from the user agent string
    return extractWebViewVersion(webViewVersion) ?: "Unknown"
  }
}