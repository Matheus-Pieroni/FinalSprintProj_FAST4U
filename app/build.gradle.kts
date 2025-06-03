plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.finalsprintproj_fast4u"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.finalsprintproj_fast4u"
        minSdk = 28
        versionCode = 1
        versionName = "0.7"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildToolsVersion = "35.0.0"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.glide)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth) // ESSENCIAL para login

    implementation(libs.gms.play.services.auth) // Google Sign-In tradicional
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)

    // === Google Sign-In e Credential Manager ===
    implementation(libs.credentials.v120)
    implementation(libs.gms.play.services.auth) // v21.3.0
    implementation(libs.credentials) // v1.2.0-alpha02
    implementation(libs.credentials.play.services.auth) // v1.5.0
    implementation(libs.googleid) // v1.1.1

    // Firebase
    implementation(platform(libs.firebase.bom)) // BoM v33.14.0
    implementation(libs.firebase.analytics)

    // UI
    implementation(libs.appcompat) // v1.7.0
    implementation(libs.material) // v1.12.0
    implementation(libs.constraintlayout) // v2.2.1
    implementation(libs.cardview) // v1.0.0
    implementation(libs.circleimageview)
    implementation(libs.firebase.auth) // v3.1.0

    // Testes
    testImplementation(libs.junit) // v4.13.2
    androidTestImplementation(libs.junit.v115) // v1.1.5
    androidTestImplementation(libs.espresso.core.v351) // v3.5.1
}
