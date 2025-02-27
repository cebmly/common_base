plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
//    id("com.alibaba.arouter")
}

android {
    namespace = "com.feb.lib_common"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
    android.buildFeatures.dataBinding = true
    buildFeatures {
        viewBinding = true
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
    implementation(libs.androidx.databinding.runtime)
    implementation(project(":lib_base"))
    implementation(project(":lib_utils"))
    implementation(project(":net"))
//    implementation(project(":FlycoTabLayout_Lib"))
    api(project(":FlycoTabLayout_Lib"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.utilcode)
    api(libs.pictureselector)
    api(libs.glide)
    api(libs.roundedimageview)
    // ARouter 核心库
    implementation("com.alibaba:arouter-api:1.5.2")

    // ARouter 注解处理器
    kapt("com.alibaba:arouter-compiler:1.5.2")
    api(libs.tablayout)
    api("com.github.angcyo.DslTablayout:ViewPager2Delegate:1.4.2")
    api("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4")
}