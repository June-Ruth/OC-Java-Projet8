subdir("applications") {
    include("admin")
    include("dummy")
    include("localization")
    include("rewards-calculator")
    include("user-profile")
    include("webapp")
}

subdir("core-librairies") {
    include("gps-utils")
    include("reward-central")
    include("trip-pricer")
    include("models")
}

class IncludeDsl(val root: String) {
    fun include(project: String) {
        settings.include(project)
        settings.project(":$project").also {
            it.projectDir = file("$root/$project")
        }
    }
}

fun subdir(root: String, block: IncludeDsl.() -> Unit) {
    block(IncludeDsl(root))
}