import com.android.build.gradle.BaseExtension
import extensions.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class ConfigureAndroid : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureAndroidBlock()
    }

    private fun Project.configureAndroidBlock() =
        this.extensions
            .getByType<BaseExtension>()
            .run {
                compileSdkVersion(
                    libs
                        .findVersion("compileSdkVersion")
                        .get()
                        .toString()
                        .toInt(),
                )

                defaultConfig {
                    minSdk =
                        libs
                            .findVersion("minSdkVersion")
                            .get()
                            .toString()
                            .toInt()
                    targetSdk =
                        libs
                            .findVersion("compileSdkVersion")
                            .get()
                            .toString()
                            .toInt()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    vectorDrawables.useSupportLibrary = true
                    resourceConfigurations.addAll(
                        setOf(
                            "en",
                            "ar",
                        ),
                    )
                }

                buildFeatures.buildConfig = true

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                        freeCompilerArgs.addAll(
                            listOf(
                                "-Xopt-in=kotlin.RequiresOptIn",
                                "-Xstring-concat=inline",
                            ),
                        )
                    }
                }

                packagingOptions {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
}
