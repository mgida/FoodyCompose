import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.foody"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foody"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val apiKey = properties.getProperty("API_KEY") ?: ""

        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Retrofit
    implementation(libs.bundles.retrofit)
    implementation(libs.mock.webserver)

    // Mockito
    testImplementation(libs.bundles.mockito)

    // Hilt
    implementation(libs.bundles.hilt)
    ksp(libs.dagger.hilt.compiler)

    // Coil
    implementation(libs.coil)

    // Timber
    implementation(libs.timber)

    // Navigation
    implementation(libs.androidx.navigation)

    // Pager & Constraint Layout
    implementation(libs.pager)
    implementation(libs.constraint.layout)

    // Splash
    implementation(libs.splash)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // DataStore
    implementation(libs.data.store)

    // Unit Testing
    testImplementation(libs.bundles.unitTest)
    implementation(libs.androidx.truth)

    // Instrumentation Testing
    androidTestImplementation(libs.bundles.instrumentationTest)

    // Compose UI Test
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.uiTest)
    debugImplementation(libs.androidx.ui.tooling)
}