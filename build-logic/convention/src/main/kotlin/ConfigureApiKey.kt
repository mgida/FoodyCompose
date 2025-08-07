import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Properties

class ConfigureApiKey : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            val apiKeyFile = project.rootProject.file("apikeys.properties")
            val properties = Properties()
            properties.load(apiKeyFile.inputStream())

            val apiKey = properties.getProperty("API_KEY").orEmpty()

            project.extensions.findByType(BaseExtension::class.java)?.defaultConfig?.apply {
                buildConfigField(
                    type = "String",
                    name = "API_KEY",
                    value = apiKey,
                )
            }
        }
    }
}
