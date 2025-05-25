plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
}

android {
    compileSdk = 33
    namespace = "com.example.myapplication"
    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
     
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.0")
}