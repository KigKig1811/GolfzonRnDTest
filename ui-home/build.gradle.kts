import com.xt.golfzon.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.xt.ui_home"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // modules
    implementation(project(Modules.CORE_BASE))
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.RESOURCE))
    implementation(project(Modules.SHARED))
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.paging.runtime.ktx)

    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)

    // androidX
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.material)

    // navigation
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // coroutines
    implementation(libs.coroutines)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // image loading
    implementation(libs.glide)

    //timber
    implementation(libs.timber)

}
