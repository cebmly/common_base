plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.feb.module_splash"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
    //noinspection DataBindingWithoutKapt
    android.buildFeatures.dataBinding = true
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
    kapt {

        arguments {
            arg("AROUTER_MODULE_NAME", project.name)
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(":lib_common"))
    implementation(project(":lib_utils"))
    implementation(project(":module_home"))
    implementation(project(":module_dynamic"))
    implementation(project(":module_live"))
    implementation(project(":module_mine"))
    implementation(libs.utilcode)
    implementation(libs.retrofit)
    // ARouter 核心库
    implementation("com.alibaba:arouter-api:1.5.2")

    // ARouter 注解处理器
    kapt("com.alibaba:arouter-compiler:1.5.2")
}