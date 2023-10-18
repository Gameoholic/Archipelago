package com.github.gameoholic

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Archipelago : ModInitializer {
    private val modID = "archipelago"
    private val logger = LoggerFactory.getLogger(modID)

	override fun onInitialize() {
		logger.info("Hello Fabric world!")
	}
}