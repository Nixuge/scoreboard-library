import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("net.megavex.scoreboardlibrary.base-conventions")
  id("net.megavex.scoreboardlibrary.publish-conventions")
  alias(libs.plugins.kotlin)
}

kotlin {
  explicitApi()
}

dependencies {
  api(project(":scoreboard-library-api"))
  testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}
