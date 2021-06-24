buildscript {
    val kotlin_version by extra("1.4.31")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.android.tools.build:gradle:4.2.1")
        val kotlinVersion = "1.5.0"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}