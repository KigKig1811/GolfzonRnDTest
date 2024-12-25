import com.xt.golfzon.Modules

plugins {
    alias (libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.xt.share"
}

dependencies {
    // modules
    implementation(project(Modules.RESOURCE))

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // gson
    implementation(libs.retrofit.gson)

    //timber
    implementation(libs.timber)

}
