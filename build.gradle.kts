import toni.blahaj.*
import toni.blahaj.api.*

val templateSettings = object : BlahajSettings {
	// -------------------- Dependencies ---------------------- //
	override val depsHandler: BlahajDependencyHandler get() = object : BlahajDependencyHandler {
		override fun addGlobal(mod : ModData, deps: DependencyHandler) {
			val emfVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.20.1" -> "QoWmvvjv"
				"fabric-1.21.1" -> "Qql6TI9W"
				"neoforge-1.21.1" -> "gijBk6cS"
				"forge-1.20.1" -> "2.2.6"
				else -> null
			}

			val etfVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.20.1" -> "3VwlPPf2"
				"fabric-1.21.1" -> "KmnvNiTO"
				"neoforge-1.21.1" -> "vVFfQs14"
				"forge-1.20.1" -> "6.2.8"
				else -> null
			}

			val dynFPSVersion = when(mod.loader + "-" + mod.mcVersion) {
				"fabric-1.20.1" -> "l1WIlBBy"
				"fabric-1.21.1" -> "nGAANyOn"
				"neoforge-1.21.1" -> "cijcctgZ"
				"forge-1.20.1" -> "sUx9EdQD"
				else -> null
			}

			if (emfVersion != null) deps.modImplementation(modrinth("entity-model-features", emfVersion))
			if (etfVersion != null) deps.modImplementation(modrinth("entitytexturefeatures", etfVersion))
			if (dynFPSVersion != null) deps.modImplementation(modrinth("dynamic-fps", dynFPSVersion))

			deps.modImplementation("toni.sodiumoptionsapi:${mod.loader}-${mod.mcVersion}:1.0.7") { isTransitive = false }
		}

		override fun addFabric(mod : ModData, deps: DependencyHandler) {
			if (mod.mcVersion == "1.21.1")
			{
				deps.modImplementation(modrinth("sodium", "mc1.21.1-0.6.0-fabric"))
				deps.modImplementation(modrinth("reeses-sodium-options", "mc1.21.3-1.8.0+fabric"))
			}
			else
			{
				deps.modImplementation(modrinth("sodium", "mc1.20.1-0.5.11"))
				deps.modImplementation(modrinth("reeses-sodium-options", "mc1.20.1-1.7.2"))
			}
		}

		override fun addForge(mod : ModData, deps: DependencyHandler) {
			deps.modImplementation(modrinth("embeddium", "0.3.31+mc1.20.1"))
		}

		override fun addNeo(mod : ModData, deps: DependencyHandler) {
			deps.implementation(modrinth("sodium", "mc1.21.1-0.6.0-neoforge"))
			deps.implementation(modrinth("reeses-sodium-options", "mc1.21.3-1.8.0+neoforge"))
		}
	}

	// ---------- Curseforge/Modrinth Configuration ----------- //
	// For configuring the dependecies that will show up on your mod page.
	override val publishHandler: BlahajPublishDependencyHandler get() = object : BlahajPublishDependencyHandler {
		override fun addShared(mod : ModData, deps: DependencyContainer) {
			if (mod.isFabric) {
				deps.requires("fabric-api")
			}
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
	id("toni.blahaj") version "1.0.9"
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
}
