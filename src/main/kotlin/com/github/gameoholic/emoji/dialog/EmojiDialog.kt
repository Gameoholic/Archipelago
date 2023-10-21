package com.github.gameoholic.emoji.dialog

import com.github.gameoholic.mixin.ChatScreenInvoker
import com.noxcrew.sheeplib.dialog.Dialog
import com.noxcrew.sheeplib.layout.grid
import com.noxcrew.sheeplib.theme.Theme
import com.noxcrew.sheeplib.theme.Themed
import com.noxcrew.sheeplib.widget.ThemedButton
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.StringWidget
import net.minecraft.client.gui.screens.ChatScreen
import net.minecraft.network.chat.Component


class EmojiDialog(x: Int, y: Int) : Dialog(x, y), Themed by EmojiDialogTheme.theme {
    override fun layout() = grid {
        // Some static text.
        StringWidget(Component.literal("Emojis"), Minecraft.getInstance().font).at(0, 0)

        var currentColumn = 0
        fun createEmojiButton(buttonMessage: Component, emojiValue: String): ThemedButton {
            val btn = ThemedButton(
                buttonMessage,
                centreText = true,
                theme = this@EmojiDialog
            ) {
                val mc = Minecraft.getInstance()
                val chatScreen = (mc.screen as? ChatScreen)
                (chatScreen as? ChatScreenInvoker)?.invokeInsertText(emojiValue, false)
            }.at(1, currentColumn)
            currentColumn++
            return btn
        }

        val emojis = mapOf(
            Component.literal("\uD83D\uDE0E") to ":sunglasses:",
            Component.literal("\uD83E\uDD21") to ":clown:",
            Component.literal("\uD83E\uDD13") to ":nerd:",
            Component.literal("\uD83D\uDC80") to ":skull:",
        )
        emojis.forEach(::createEmojiButton)


        ThemedButton(
            Component.literal("X"),
            theme =  this@EmojiDialog
        ) {
            close()
        }.at(2, 0)

        // GridLayoutBuilder has some nice ways of adding components
        // without having to give them all row and column values manually,
        // but that's outside the scope of this example.
        // Check GridLayoutBuilder javadocs for more info.
    }



}