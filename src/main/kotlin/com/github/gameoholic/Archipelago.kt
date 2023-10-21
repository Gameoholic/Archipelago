package com.github.gameoholic

import com.github.gameoholic.emoji.EmojiDialogUtil
import com.github.gameoholic.emoji.dialog.EmojiDialog
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.noxcrew.sheeplib.DialogContainer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl
import net.minecraft.network.Connection
import org.slf4j.LoggerFactory

object Archipelago : ClientModInitializer {
    private const val MOD_ID = "archipelago"
    val ISLAND_IP_KEYWORDS = listOf("island", "ilovecatboys")
    private val logger = LoggerFactory.getLogger(MOD_ID)
    var connectedToIsland = false

    override fun onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            dispatcher.register(EmojiDialogUtil.EMOJI_COMMAND)
        }
    }
}