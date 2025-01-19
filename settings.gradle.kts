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
        maven(url = "https://dl.cloudsmith.io/public/cometchat/cometchat/maven/")
    }
}
//This is setting.gradle
rootProject.name = "CometChatAndroidSample"
include(":app")
 