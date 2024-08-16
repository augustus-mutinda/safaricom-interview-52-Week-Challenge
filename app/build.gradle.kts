plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.compose)
}

android {
    namespace = "com.safaricom.savings"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.safaricom.savings"
        minSdk = 26
        targetSdk = 34

        val properties = rootProject.extra.properties
        val appVersionName = properties["appVersionName"] as String
        val appVersionCode = (properties["appVersionCode"] as String).toInt()

        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        setProperty("archivesBaseName", "${applicationId}-v${versionName}(${versionCode})")

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("boolean", "DEBUG", "false")
            isMinifyEnabled = false
        }
        debug {
            buildConfigField("boolean", "DEBUG", "true")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

//    signingConfigs {
//        register("release")
//
//        forEach { config ->
//            val props = Properties()
//            val keyPath = "signingKeys/${config.name}Signing.properties"
//            props.load(project.rootProject.file(keyPath).inputStream())
//
//            config.keyAlias = props["signing.key.alias"] as String
//            config.keyPassword = props["signing.key.password"] as String
//            config.storeFile = file(props["signing.store.file"] as Any)
//            config.storePassword = props["signing.store.password"] as String
//
//            config.enableV3Signing = config.name == "release"
//            config.enableV4Signing = config.name == "release"
//        }
//    }

    kotlinOptions {
        jvmTarget = "17"
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.timber)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.viewmodel.compose)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(project(path = ":data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}