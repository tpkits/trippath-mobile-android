plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "trippath.android.application"
            implementationClass = "com.sejun2.trippath.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "trippath.android.library"
            implementationClass = "com.sejun2.trippath.AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "trippath.android.compose"
            implementationClass = "com.sejun2.trippath.AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "trippath.android.hilt"
            implementationClass = "com.sejun2.trippath.AndroidHiltConventionPlugin"
        }
    }
}
