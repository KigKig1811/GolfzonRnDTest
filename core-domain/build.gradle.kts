import com.xt.golfzon.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.xt.core_domain"
}

dependencies {
    // modules
    implementation(project(Modules.SHARED))
    implementation(project(Modules.RESOURCE))

    // core
    implementation(libs.androidx.core.ktx)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)

    // hilt - di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // timber
    implementation(libs.timber)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}
