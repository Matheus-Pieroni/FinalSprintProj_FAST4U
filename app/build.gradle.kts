// app/build.gradle.kts (Module: app)

plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Este plugin DEVE estar aqui (sem apply false)
}

android {
    namespace = "com.example.finalsprintproj_fast4u"
    compileSdk = 35 // Ou sua versão de compileSdk

    defaultConfig {
        applicationId = "com.example.finalsprintproj_fast4u"
        minSdk = 28 // Ou sua versão de minSdk
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
}

dependencies {
    // === ADICIONE ESTAS DUAS NOVAS LINHAS PARA CredentialManager ===

    // Credential Manager
    implementation(libs.credentials.v150)

    // Google Identity Services com suporte à CredentialManager
    implementation(libs.googleid.v110alpha02)

    implementation(libs.credentials) // Verifique a versão mais recente!
    implementation(libs.credentials.play.services.auth) // Verifique a versão mais recente!
    //

    // Suas dependências existentes
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material) // Exemplo

    implementation(libs.gms.play.services.auth) // <-- SUBSTITUA '21.0.0' PELA VERSÃO MAIS RECENTE QUE VOCÊ ENCONTROU

    // Import the Firebase BoM
    implementation(platform(libs.firebase.bom))

    // Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation(libs.firebase.analytics)

    // === ADICIONE ESTA LINHA PARA O GOOGLE SIGN-IN AQUI ===
    implementation(libs.play.services.auth)
    implementation(libs.googleid)
    // ===============================================

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)
}