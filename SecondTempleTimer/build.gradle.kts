plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

group = "com.kdroid.secondtempletimer"
version = "0.1"

kotlin {
    jvmToolchain(11)
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    js {
        browser {
            webpackTask {
                mainOutputFileName = "SecondTempleTimer.js"
            }
        }
        binaries.executable()
    }

    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "SecondTempleTimer"
            isStatic = true
        }
    }

    listOf(
        macosX64(),
        macosArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "SecondTempleTimer"
            isStatic = true
        }
    }

    linuxX64 {
        binaries.staticLib {
            baseName = "SecondTempleTimer"
        }
    }

    mingwX64 {
        binaries.staticLib {
            baseName = "SecondTempleTimer"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        jsMain.dependencies {
            implementation(npm("@js-joda/timezone", "2.3.0"))
        }
    }

    // https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"]
            .compilerOptions.options.freeCompilerArgs
            .add("-Xexport-kdoc")
    }
}

android {
    namespace = "com.kdroid.secondtempletimer"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }
}
