pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven {
            url = uri("https://your-bytedance-repo-url.com/repository") // Replace this with the correct URL
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // 添加这一行以包含 JitPack
    }

}

rootProject.name = "CommonBaseApp"
include(":app")
include(":lib_common")
include(":lib_base")
include(":lib_utils")
include(":net")
include(":FlycoTabLayout_Lib")
include(":module_home")
include(":module_splash")
include(":module_dynamic")
include(":module_live")
include(":module_mine")
include(":module_discover")
