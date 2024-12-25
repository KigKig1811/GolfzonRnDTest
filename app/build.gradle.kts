import com.xt.golfzon.AndroidJUnit
import com.xt.golfzon.Configuration
import com.xt.golfzon.Modules

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.golfzon.simpletest"
    compileSdk = Configuration.COMPILE_SDK

    defaultConfig {
        applicationId = Configuration.APPLICATION_ID
        minSdk = Configuration.MIN_SDK
        targetSdk = Configuration.TARGET_SDK
        versionCode = Configuration.VERSION_CODE
        versionName = Configuration.VERSION_NAME
        testInstrumentationRunner = AndroidJUnit.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // modules
    implementation(project(Modules.CORE_BASE))
    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.RESOURCE))
    implementation(project(Modules.SHARED))
    implementation(project(Modules.UI_HOME))

    // androidX
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.startup)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.process)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)
    kspAndroidTest(libs.hilt.compiler)

    // coroutines
    implementation(libs.coroutines)

    // retrofit
    implementation(libs.retrofit)

    // image loading
    implementation(libs.glide)

    // transformation animation
    implementation(libs.transformationLayout)

    // recyclerView
    implementation(libs.recyclerview)

    // timber
    implementation(libs.timber)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}
