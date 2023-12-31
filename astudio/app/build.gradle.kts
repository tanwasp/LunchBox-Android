import java.util.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}



android {
    namespace = "edu.vassar.cmpu203.lunchbox"
    compileSdk = 34

    testOptions {
        animationsDisabled = true
    }



    defaultConfig {
        applicationId = "edu.vassar.cmpu203.lunchbox"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

//        buildConfigField("String", "PLACES_API_KEY", "\"${localProperties.getProperty("PLACES_API_KEY")}\"")
        buildConfigField("String", "PLACES_API_KEY", "\"${localProperties.getProperty("PLACES_API_KEY")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "PLACES_API_KEY", "\"${localProperties.getProperty("PLACES_API_KEY")}\"")
        }

        debug {
//            buildConfigField("String", "PLACES_API_KEY", "PLACES_API_KEY")
            buildConfigField("String", "PLACES_API_KEY", "\"${localProperties.getProperty("PLACES_API_KEY")}\"")
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
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


    dependencies {
        implementation("com.google.android.libraries.places:places:2.4.0")
        implementation("com.android.volley:volley:1.2.1")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.10.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation(platform("androidx.compose:compose-bom:2023.03.00"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.annotation:annotation:1.6.0")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
        implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
        testImplementation("junit:junit:4.13.2")
        testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
        testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
        androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.5.1"){
            exclude("com.google.protobuf","protobuf-lite");
        }
        androidTestImplementation("androidx.test:rules:1.4.0")
        androidTestImplementation("androidx.test.uiautomator:uiautomator:2.2.0")


        // Import the Firebase BoM
        implementation(platform("com.google.firebase:firebase-bom:32.6.0"))


        // TODO: Add the dependencies for Firebase products you want to use
        // When using the BoM, don't specify versions in Firebase dependencies
        implementation("com.google.firebase:firebase-analytics")
        implementation("com.google.firebase:firebase-auth") // Firebase Auth SDK
//        implementation("com.firebaseui:firebase-ui-auth") // FirebaseUI for Auth
        implementation("com.firebaseui:firebase-ui-auth:8.0.2")
        implementation("com.google.firebase:firebase-core:21.1.1") // Firebase Core
        implementation("com.google.firebase:firebase-firestore:24.9.1")
        implementation("com.google.android.gms:play-services-location:17.0.0")
    }

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

}
dependencies {
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
}
