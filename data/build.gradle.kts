plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.safaricom.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        val properties = rootProject.extra.properties
        val appVersionName = properties["appVersionName"] as String
        val appVersionCode = (properties["appVersionCode"] as String).toInt()
        val appSecure = properties["appSecure"] as Boolean

        buildConfigField("boolean", "DEBUG", "false")
        buildConfigField("boolean", "SECURE", "$appSecure")

        buildConfigField("String", "VERSION_NUMBER", "\"${appVersionCode}\"")
        buildConfigField("String", "VERSION_NAME", "\"${appVersionName}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.room.runtime)
    implementation("androidx.room:room-ktx:2.5.0")
    implementation(libs.core.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.koin.android)
    implementation(libs.androidx.security.crypto)
    implementation(libs.koin.androidx.compose)

    // For testing Room
    testImplementation("androidx.room:room-testing:2.5.0")
    // For Koin testing
    testImplementation("io.insert-koin:koin-test:3.3.0")
    // For coroutine testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
    // For general testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.6.1")
    testImplementation("org.mockito:mockito-inline:4.6.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}