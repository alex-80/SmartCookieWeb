package com.cookiegames.smartcookie.core.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenLink @Inject constructor(
  @ApplicationContext private val context: Context
) {
  operator fun invoke(url: String) {
    val shareIntent = Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse(url)
      flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    context.startActivity(shareIntent)
  }
}