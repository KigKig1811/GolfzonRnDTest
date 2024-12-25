package com.xt.golfzon

import org.gradle.configurationcache.extensions.capitalized

sealed class BuildType(
    val name: String,
    val isMinifyEnabled: Boolean,
    val proguard: String
) {
    object Debug : BuildType("debug", isMinifyEnabled = false, proguard = "proguard-debug.pro")
    object Release : BuildType("release", isMinifyEnabled = true, proguard = "proguard-rules.pro")
}

interface Field {
    val type: String
    val name: String
    val value: String
}

data class ResValueField(
    override val type: String,
    override val name: String,
    override val value: String
) : Field

data class BuildConfigField(
    override val type: String,
    override val name: String,
    override val value: String
) : Field

sealed class ProductFlavor(
    val name: String,
    val versionNameSuffix: String,
    vararg val fields: Field
) {
    object Dev : ProductFlavor(
        name = "dev",
        versionNameSuffix = "",
        BuildConfigField(
            type = String::class.java.simpleName,
            name = "BASE_URL",
            value = "\"https://api.thecatapi.com/\""
        ),
    )

    object Product : ProductFlavor(
        name = "product",
        versionNameSuffix = "",
        BuildConfigField(
            type = String::class.java.simpleName,
            name = "BASE_URL",
            value = "\"https://api.thecatapi.com/\""
        ),
    )
}
object AndroidJUnit {
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}


operator fun ProductFlavor.plus(buildType: BuildType) = "$name${buildType.name.capitalized()}"

val IGNORE_VARIANTS = listOf<String>() // declare any variants to ignore here
