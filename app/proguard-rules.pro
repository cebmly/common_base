# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# need add for Fragment page route
# -keep public class * extends android.app.Fragment
# -keep public class * extends androidx.fragment.app.Fragment
# -keep public class * extends android.support.v4.app.Fragment
# Keep ARouter core classes
-keep class com.alibaba.android.arouter.** { *; }

# Keep ARouter route group and root classes
-keep class * extends com.alibaba.android.arouter.facade.template.IRouteGroup { *; }
-keep class * extends com.alibaba.android.arouter.facade.template.IRouteRoot { *; }

# Keep classes annotated with @Route
-keep @com.alibaba.android.arouter.annotation.Route class * { *; }

# Keep fields annotated with @Autowired
-keepclassmembers class * {
    @com.alibaba.android.arouter.annotation.Autowired <fields>;
}

# Keep ARouter group classes
-keep class com.alibaba.android.arouter.routes.** { *; }

# Keep ARouter path classes
-keep class com.alibaba.android.arouter.routes.** { *; }

