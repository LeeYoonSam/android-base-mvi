plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ys.core.data"
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
    implementation(project(":core:network"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}