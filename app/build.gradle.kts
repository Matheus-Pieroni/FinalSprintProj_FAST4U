/*val org.gradle.accessors.dm.LibrariesForLibs.FirebaseLibraryAccessors.admin: kotlin.Any;

val org.gradle.api.provider.Provider<org.gradle.api.artifacts.MinimalExternalModuleDependency>.v3430: kotlin.Any;
*/

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
        versionName = "1.2"

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

    // Use the newer Firebase BOM to manage all Firebase dependency versions
    implementation(platform(libs.firebase.bom.v3430))

    // Firebase Libraries
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth) // ESSENCIAL para login
    implementation(libs.google.firebase.firestore)

    // Google Sign-In and Credential Manager
    // These are modern libraries for handling sign-in.
    implementation(libs.gms.play.services.auth) // Google Sign-In
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    // UI and Utility Libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.cardview)
    implementation(libs.glide)
    implementation(libs.circleimageview)

    // Test Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)
}
