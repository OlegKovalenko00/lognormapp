plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
}

detekt {
    config = files("detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
}

android {
    namespace = "com.example.itog1"
    compileSdk = 34
    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/INDEX.LIST")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("git.properties")
    }
    defaultConfig {
        applicationId = "com.example.itog1"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Основные библиотеки
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.dataframe)

    // Для тестирования
    testImplementation(libs.junit)
    testImplementation(libs.commonsLang)
    testImplementation(libs.commons.io.v2130)

    // Для тестов Android
    androidTestImplementation("androidx.core:core-ktx:1.13.0") // Добавлено для устранения конфликта
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("io.github.kakaocup:kakao:3.1.0") // Обновлено

    // Дополнительные библиотеки
    implementation(libs.commons.math3)
}
