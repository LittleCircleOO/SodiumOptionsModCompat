import toni.blahaj.*
import toni.blahaj.api.*

val templateSettings = object : BlahajSettings {
	// -------------------- Dependencies ---------------------- //
	override val depsHandler: BlahajDependencyHandler get() = object : BlahajDependencyHandler {
		override fun addGlobal(mod : ModData, deps: DependencyHandler) {
			val emfVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.21.4" -> "n0dDeW4R"
				"fabric-1.21.1" -> "9t01xL7K"
				"fabric-1.20.1" -> "X2NBK99f"
				"neoforge-1.21.4" -> "dYsCCt6Z"
				"neoforge-1.21.1" -> "Z0UKemxw"
				"forge-1.20.1" -> "2.4.1"
				else -> null
			}

			val etfVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.21.4" -> "Aan0aaUL"
				"fabric-1.21.1" -> "zzyLrMkD"
				"fabric-1.20.1" -> "WvkMQbYb"
				"neoforge-1.21.4" -> "bejTYsON"
				"neoforge-1.21.1" -> "jmfAD9oz"
				"forge-1.20.1" -> "6.2.9"
				else -> null
			}

			val dynFPSVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.21.4" -> "lCPnAoak"
				"fabric-1.21.1" -> "nGAANyOn"
				"fabric-1.20.1" -> "l1WIlBBy"
				"neoforge-1.21.4" -> "m1fZGieh"
				"neoforge-1.21.1" -> "cijcctgZ"
				"forge-1.20.1" -> "sUx9EdQD"
				else -> null
			}

			val continuityVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.21.4" -> "3.0.0+1.21.4"
				"fabric-1.21.1" -> "3.0.0-beta.5+1.21"
				"fabric-1.20.1" -> "3.0.0-beta.5+1.20.1"
				"neoforge-1.21.4" -> "3.0.0+1.21.neoforge"
				"neoforge-1.21.1" -> "3.0.0-beta.5+1.21"
				"forge-1.20.1" -> "3.0.0-beta.5+1.20.1"
				else -> null
			}

			if (continuityVersion != null)
			{
				if (mod.isFabric)
					deps.modImplementation(modrinth("continuity", continuityVersion))
				else
					deps.compileOnly(modrinth("continuity", continuityVersion))
			}

			if (emfVersion != null) deps.modImplementation(modrinth("entity-model-features", emfVersion))
			if (etfVersion != null) deps.modImplementation(modrinth("entitytexturefeatures", etfVersion))
			if (dynFPSVersion != null) deps.modImplementation(modrinth("dynamic-fps", dynFPSVersion))

			deps.modImplementation("toni.sodiumoptionsapi:${mod.loader}-${mod.mcVersion}:1.0.9") { isTransitive = false }
			
			if (mod.loader + "-" + mod.mcVersion != "neoforge-1.21.4")
				deps.modImplementation("toni.txnilib:${mod.loader}-${mod.mcVersion}:1.0.21")
			
			deps.runtimeOnly("net.lostluma:battery:1.3.0")
		}

		override fun addFabric(mod : ModData, deps: DependencyHandler) {
			deps.include(deps.implementation(deps.annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:0.2.0-beta.6")!!)!!)
			deps.modImplementation(modrinth("sodium", when (mod.mcVersion) {
				"1.21.4" -> "mc1.21.4-0.6.6-fabric"
				"1.21.1" -> "mc1.21.1-0.6.5-fabric"
				"1.20.1" -> "mc1.20.1-0.5.11"
				else -> ""
			}))

			deps.modImplementation(modrinth("reeses-sodium-options", when (mod.mcVersion) {
				"1.21.4" -> "mc1.21.4-1.8.2+fabric"
				"1.21.1" -> "mc1.21.3-1.8.0+fabric"
				"1.20.1" -> "mc1.20.1-1.7.2"
				else -> ""
			}))
		}

		override fun addForge(mod : ModData, deps: DependencyHandler) {
			deps.modImplementation(modrinth("embeddium", "0.3.31+mc1.20.1"))
			deps.minecraftRuntimeLibraries("net.lostluma:battery:1.3.0")
		}

		override fun addNeo(mod : ModData, deps: DependencyHandler) {
			deps.modImplementation(modrinth("sodium", when (mod.mcVersion) {
				"1.21.4" -> "mc1.21.4-0.6.6-neoforge"
				"1.21.1" -> "mc1.21.1-0.6.0-neoforge"
				else -> ""
			}))

			deps.modImplementation(modrinth("reeses-sodium-options", when (mod.mcVersion) {
				"1.21.4" -> "mc1.21.4-1.8.2+neoforge"
				"1.21.1" -> "mc1.21.3-1.8.0+neoforge"
				else -> ""
			}))

			when (mod.mcVersion) {
				"1.21.4" -> {
					deps.compileOnly("org.sinytra.forgified-fabric-api:fabric-api-base:0.4.42+d1308ded19")
					deps.compileOnly("org.sinytra.forgified-fabric-api:fabric-renderer-api-v1:5.0.0+babc52e504")
					deps.compileOnly("org.sinytra.forgified-fabric-api:fabric-rendering-data-attachment-v1:0.3.48+73761d2e19")
					deps.compileOnly("org.sinytra.forgified-fabric-api:fabric-block-view-api-v2:1.0.10+9afaaf8c19")
				}
				"1.21.1" -> {
					deps.compileOnly("org.sinytra.forgified-fabric-api:fabric-api-base:0.4.42+d1308dedd1")
					deps.compileOnly("org.sinytra.forgified-fabric-api:fabric-renderer-api-v1:3.4.0+acb05a39d1")
				}
			}


			deps.minecraftRuntimeLibraries("net.lostluma:battery:1.3.0")
		}
	}

	// ---------- Curseforge/Modrinth Configuration ----------- //
	// For configuring the dependecies that will show up on your mod page.
	override val publishHandler: BlahajPublishDependencyHandler get() = object : BlahajPublishDependencyHandler {
		override fun addShared(mod : ModData, deps: DependencyContainer) {
			if (mod.isFabric) {
				deps.requires("fabric-api")
			}

			deps.requires("sodium-options-api")
		}

		override fun addCurseForge(mod : ModData, deps: DependencyContainer) {

		}

		override fun addModrinth(mod : ModData, deps: DependencyContainer) {

		}
	}
}

plugins {
	`maven-publish`
	application
	id("toni.blahaj") version "1.0.15"
	kotlin("jvm")
	kotlin("plugin.serialization")
	id("dev.kikugie.j52j") version "1.0"
	id("dev.architectury.loom")
	id("me.modmuss50.mod-publish-plugin")
	id("systems.manifold.manifold-gradle-plugin")
}

blahaj {
	sc = stonecutter
	settings = templateSettings
	init()
}

// Dependencies
repositories {
	maven("https://maven.pkg.github.com/ims212/ForgifiedFabricAPI") {
		credentials {
			username = "IMS212"
			// Read only token
			password = "ghp_" + "DEuGv0Z56vnSOYKLCXdsS9svK4nb9K39C1Hn"
		}
	}
	maven("https://www.cursemaven.com")
	maven("https://api.modrinth.com/maven")
	maven("https://thedarkcolour.github.io/KotlinForForge/")
	maven("https://maven.kikugie.dev/releases")
	maven("https://maven.txni.dev/releases")
	maven("https://jitpack.io")
	maven("https://maven.neoforged.net/releases/")
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
	maven("https://maven.parchmentmc.org")
	maven("https://maven.su5ed.dev/releases")
	maven("https://maven.su5ed.dev/releases")
	maven("https://maven.fabricmc.net")
	maven("https://maven.shedaniel.me/")
	maven("https://maven.lostluma.net/releases")
}
