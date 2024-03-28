plugins {
  id("net.megavex.scoreboardlibrary.base-conventions")
  id("net.megavex.scoreboardlibrary.publish-conventions")
}

repositories {
  maven("https://nexus.funkemunky.cc/content/repositories/releases/")
}

dependencies {
  compileOnly(project(":scoreboard-library-packet-adapter-base"))
  compileOnly(libs.onePointEightPointEightNms)
  testImplementation(libs.onePointEightPointEightNms)
}
