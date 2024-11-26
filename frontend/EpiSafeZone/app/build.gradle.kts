import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

val customProperties = Properties()
val customPropertiesFile = rootProject.file("custom.properties")
if (customPropertiesFile.exists()) {
    customPropertiesFile.inputStream().use { stream ->
        customProperties.load(stream)
    }
}

val API_LINK = customProperties["API_LINK"] ?: ""

android {
    namespace = "com.example.episafezone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.episafezone"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_LINK", "\"${API_LINK}\"")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding=true
        buildConfig = true
    }
    packaging {
        resources {
            excludes.add("META-INF/INDEX.LIST")
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/LICENSE-notice.md")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.github.prolificinteractive:material-calendarview:1.4.3")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.volley)
    implementation(libs.commons.lang3)
    implementation(libs.appium.java.client)
    implementation(libs.logback.classic)
    implementation(libs.selenium.java.v4100)
    testImplementation(libs.selenium.java)
    testImplementation(libs.junit)
    testImplementation(libs.jupiter.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.jupiter.junit.jupiter.api)
    androidTestRuntimeOnly(libs.junit.jupiter.engine)
}