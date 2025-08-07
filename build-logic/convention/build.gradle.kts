import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    `kotlin-dsl`
}

version = 1.0

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks
    .withType<KotlinCompile>()
    .configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("configureAndroid") {
            id = "gradletools.configureandroid"
            implementationClass = "ConfigureAndroid"
        }
        register("configureApiKey") {
            id = "gradletools.configureapikey"
            implementationClass = "ConfigureApiKey"
        }
    }
}
