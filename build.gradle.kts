// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val versionMajor = 0
    val versionMinor = 0
    val versionPatch = 0
    val versionIteration = 1
    var versionClassifier: String? = null
    val isSnapshot = false

    val appVersionCode =
        ((versionMajor * 10000) + (versionMinor * 100) + (versionPatch * 10) + (versionIteration)).toString()
    project.extra.set("appVersionCode", appVersionCode)

    val appVersionName = let {
        var versionName: String? =
            "${versionMajor}.${versionMinor}.${versionPatch}.${versionIteration}"

        if (versionClassifier == null && isSnapshot) versionClassifier = "SNAPSHOT"

        if (versionClassifier != null) versionName += "-$versionClassifier"

        versionName
    }
    project.extra.set("appVersionName", appVersionName)
    project.extra.set("appSecure", true)
//    extra.set("appSecure", false)

    extra["appVersionCode"] = appVersionCode
    extra["appVersionName"] = appVersionName
}

ext {
    val versionMajor = 1
    val versionMinor = 2
    val versionPatch = 0
    val versionIteration = 15
    var versionClassifier: String? = null
    val isSnapshot = false

    fun appVersionCode(): Int {
        return (versionMajor * 10000) + (versionMinor * 100) + (versionPatch * 10) + (versionIteration)
    }

    fun appVersionName(): String {
        var versionName = "${versionMajor}.${versionMinor}.${versionPatch}.${versionIteration}"

        if (isSnapshot) versionClassifier = "SNAPSHOT"

        if (versionClassifier != null) versionName += "-$versionClassifier"

        return versionName
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}