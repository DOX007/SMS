// Root build.gradle.kts – inget plugin ska aktiveras här

plugins {
    alias(libs.plugins.android) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.androidLibrary) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

