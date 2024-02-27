package com.cookiegames.smartcookie.device

import javax.inject.Inject

/**
 * A representation of the info for the current build.
 */

data class BuildInfo @Inject constructor(val buildType: BuildType)

/**
 * The types of builds that this instance of the app could be.
 */
enum class BuildType {
  DEBUG,
  RELEASE
}
