import com.xt.golfzon.AndroidJUnit

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.plugin) apply false
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

subprojects {

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
        extensions.configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(com.xt.golfzon.Configuration.COMPILE_SDK)
            defaultConfig {
                minSdk = com.xt.golfzon.Configuration.MIN_SDK
                targetSdk = com.xt.golfzon.Configuration.TARGET_SDK

                testInstrumentationRunner = AndroidJUnit.TEST_INSTRUMENTATION_RUNNER
                consumerProguardFiles("consumer-rules.pro")

            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }
}
