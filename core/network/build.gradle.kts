plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ys.core.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        abortOnError = false
    }

    kotlinOptions {
        // Set JVM target
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    api(libs.retrofit)
    api(libs.okhttp.logging)

    api(libs.retrofit.kotlinx.serialization)
    api(libs.kotlinx.serialization.json)
}