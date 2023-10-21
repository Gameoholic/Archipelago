package com.github.gameoholic

import com.github.gameoholic.emoji.dialog.EmojiDialog
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.noxcrew.sheeplib.DialogContainer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import org.slf4j.LoggerFactory

object Archipelago : ClientModInitializer {
    private const val MOD_ID = "archipelago"
    private val logger = LoggerFactory.getLogger(MOD_ID)



    private val DIALOG = { x: Int, y: Int ->
        EmojiDialog(x, y)
    }
    private var openDialog: EmojiDialog? = null

    private val COMMAND: LiteralArgumentBuilder<FabricClientCommandSource> = ClientCommandManager.literal("emojis")
        .then(
            ClientCommandManager.argument("action", StringArgumentType.string())
                .suggests { _, builder ->
                    builder.suggest("open")
                    builder.suggest("close")
                    builder.buildFuture()
                }
                .executes {
                    val arg = it.getArgument("action", String::class.java)

                    val dialog = DIALOG(10, 10)
                    if (arg == "open") {
                        DialogContainer += dialog
                        openDialog = dialog
                    }
                    else if (arg == "close")
                        openDialog?.let(DialogContainer::minusAssign)

                    0
                }
        )

    override fun onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            dispatcher.register(COMMAND)
        }




        //DialogContainer += EmojiDialog(10, 10)
    }
}