package com.cookiegames.smartcookie.core.usecase

import com.cookiegames.smartcookie.BuildConfig
import javax.inject.Inject

class GetVersionName @Inject constructor() {
  operator fun invoke(): String {
    return BuildConfig.VERSION_NAME
  }
}