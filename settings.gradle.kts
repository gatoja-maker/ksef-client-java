plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

toolchainManagement {
    jvm {
    }
}

rootProject.name = "KSeF-sdk"
include("TestClient")
include("ksef-client", "demo-web-app")
