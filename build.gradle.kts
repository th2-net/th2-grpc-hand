plugins {
    kotlin("jvm") version "1.8.22" apply false
    id("java-library")
    id("maven-publish")

    val th2PluginVersion = "0.0.4"
    id("com.exactpro.th2.gradle.base") version th2PluginVersion
    id("com.exactpro.th2.gradle.grpc") version th2PluginVersion
    id("com.exactpro.th2.gradle.publish") version th2PluginVersion
}

allprojects {
    group = "com.exactpro.th2"
    version = project.properties["release_version"].toString()
    val suffix = project.properties["version_suffix"].toString()
    if (suffix.isNotEmpty()) {
        version = "$version-$suffix"
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
}

th2Grpc {
    service.set(true)
}

dependencies {
    api("com.exactpro.th2:grpc-common:4.4.0-dev")
}

repositories {
    mavenCentral()
    maven {
        name = "Sonatype_snapshots"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
    maven {
        name = "Sonatype_releases"
        url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
    }
}