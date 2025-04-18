plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ys.feature.home"
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

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    // 코루틴의 런타임 라이브러리(suspend 함수 실행, CoroutineScope 만들기, launch, async 같은 코루틴 빌더 사용, Dispatchers 같은 기본적인 디스패처 사용)
    // kotlinx-coroutines-core에 대한 전이 의존성(transitive dependency) 을 가지고 있습니다. 즉, 이 라이브러리를 추가하면 core 라이브러리도 자동으로 포함
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material3)
}