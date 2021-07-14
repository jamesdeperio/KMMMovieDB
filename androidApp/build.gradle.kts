plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")

}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
  //  implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("androidx.fragment:fragment-ktx:1.3.5")

    //dagger2
    implementation ("com.google.dagger:dagger-android:2.37")
    implementation ("com.google.dagger:dagger-android-support:2.37")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt("com.google.dagger:dagger-android-processor:2.37")
    kapt("com.google.dagger:dagger-compiler:2.37")

    //image processor
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("com.caverock:androidsvg-aar:1.4")

    //anim
    implementation ("com.daimajia.androidanimations:library:2.4@aar")
    implementation ("jp.wasabeef:recyclerview-animators:4.0.2")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.github.jamesdeperio.moviedb.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
        generateStubs = true
    }


}