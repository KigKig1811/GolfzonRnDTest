import com.xt.golfzon.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.xt.core_base"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // modules
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.RESOURCE))
    implementation(project(Modules.SHARED))

    // core
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // timber
    implementation(libs.timber)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // glide
    implementation(libs.glide)

    //paging
    implementation(libs.androidx.paging)
}
