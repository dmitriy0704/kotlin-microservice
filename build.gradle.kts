plugins {
    kotlin("jvm") version "2.0.20" apply false
    kotlin("plugin.spring") version "2.0.20" apply false
    id("org.springframework.boot") version "3.3.5" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

val appModules = listOf(
    "order-service",
    "user-service"
)


// ✅ Общие настройки для всех подпроектов
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    group = "dev.folomkin"
    version = "1.0.0"
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        if (name in appModules) {
            dependencies {
                add("implementation", project(":shared"))
                add("implementation", kotlin("stdlib"))
                add("implementation",
                    "org.springframework.boot:spring-boot-starter-web"
                )
                add("implementation",
                    "org.jetbrains.kotlin:kotlin-stdlib")
                add("implementation",
                    "com.fasterxml.jackson.module:jackson-module-kotlin"
                )
                add("implementation",
                    "com.fasterxml.jackson.module:jackson-module-kotlin"
                )
                add("implementation",
                    "org.jetbrains.kotlin:kotlin-reflect")
                add("testImplementation",
                    "org.springframework.boot:spring-boot-starter-test"
                )
                add("testImplementation",
                    "org.jetbrains.kotlin:kotlin-test-junit5"
                )
                add("testImplementation",
                    "org.junit.platform:junit-platform-launcher"
                )
            }
        }
    }
}
