plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.udacity.unittesting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.udacity.unittesting"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // --- TESTING DEPENDENCIES ---
    // JUnit 4
    testImplementation("junit:junit:4.13.2")
    
    // Coroutines Testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    
    // Architecture Components Testing (InstantTaskExecutorRule)
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    
    // MockK
    testImplementation("io.mockk:mockk:1.13.8")
}
