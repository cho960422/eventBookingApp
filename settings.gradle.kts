pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://devrepo.kakao.com/nexus/repository/kakaomap-releases/")
    }
}

rootProject.name = "EventBookingApp"
include(":app")
include(":presentation")
include(":domain")
include(":data")
