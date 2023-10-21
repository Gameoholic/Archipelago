package com.github.gameoholic.emoji

import com.github.gameoholic.emoji.dialog.EmojiDialog
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.noxcrew.sheeplib.DialogContainer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource

object EmojiDialogUtil {
    private var emojiDialog: EmojiDialog? = null

    val EMOJI_COMMAND: LiteralArgumentBuilder<FabricClientCommandSource> = ClientCommandManager.literal("emojis")
        .executes {
            toggleDialog()
            0
        }
    private fun toggleDialog() {
        if (emojiDialog == null)
            openDialog()
        else
            closeDialog()
    }
    fun openDialog() {
        val dialog = EmojiDialog(10, 10)
        DialogContainer += dialog
        emojiDialog = dialog
    }
    fun closeDialog() {
        emojiDialog?.close()
        emojiDialog = null
    }
}