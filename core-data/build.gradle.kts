import com.xt.golfzon.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.xt.ore_data"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
// modules
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.SHARED))

    // core
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    // datastore
    implementation(libs.datastore)

    // room database
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)

    // glide
    implementation(libs.glide)

    //timber
    implementation(libs.timber)
    implementation(libs.androidx.multidex)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}
