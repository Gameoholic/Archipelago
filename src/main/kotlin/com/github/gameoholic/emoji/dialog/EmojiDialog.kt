package com.github.gameoholic.emoji.dialog

import com.github.gameoholic.mixin.ChatScreenInvoker
import com.noxcrew.sheeplib.dialog.Dialog
import com.noxcrew.sheeplib.dialog.title.DialogTitleWidget
import com.noxcrew.sheeplib.dialog.title.TextTitleWidget
import com.noxcrew.sheeplib.layout.grid
import com.noxcrew.sheeplib.theme.Themed
import com.noxcrew.sheeplib.widget.ThemedButton
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.ChatScreen
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation

private const val COLUMNS_PER_ROW = 8
class EmojiDialog(x: Int, y: Int) : Dialog(x, y), Themed by EmojiDialogTheme.theme {
    override val title: DialogTitleWidget? = TextTitleWidget(this, Component.literal("Emojis"))
    override fun layout() = grid {
        var currentColumn = 0
        var currentRow = 0
        fun createEmojiButton(buttonMessage: Component, emojiValue: String): ThemedButton {
            val btn = ThemedButton(
                buttonMessage,
                centreText = true,
                theme = this@EmojiDialog
            ) {
                val chatScreen = Minecraft.getInstance().screen as? ChatScreen
                (chatScreen as? ChatScreenInvoker)?.invokeInsertText(emojiValue, false)
            }.at(currentRow, currentColumn)

            currentColumn++
            if (currentColumn >= COLUMNS_PER_ROW) {
                currentColumn = 0
                currentRow++
            }
            return btn
        }

        val emojiNames = listOf(
            "alien",
            "angry",
            "aqua",
            "blue",
            "burger",
            "chicken",
            "clown",
            "coins",
            "crown",
            "cyan",
            "eyes",
            "fearful",
            "flushed",
            "green",
            "grimacing",
            "grin",
            "heart",
            "heart_black",
            "heart_blue",
            "heart_eyes",
            "heart_green",
            "heart_orange",
            "heart_pink",
            "heart_purple",
            "heart_white",
            "heart_yellow",
            "hexapus",
            "imp",
            "joy",
            "kekw",
            "laughing",
            "lime",
            "mcc_angry",
            "mcc_smile",
            "music_note",
            "nerd",
            "neutral",
            "noxcrew",
            "orange",
            "pensive",
            "pink",
            "point_left",
            "point_right",
            "purple",
            "red",
            "scream",
            "skull",
            "smile",
            "sob",
            "sunglasses",
            "surprised",
            "sus",
            "sweat_smile",
            "thinking",
            "thumbs_down",
            "thumbs_up",
            "tongue_out",
            "trophy",
            "weary",
            "winking_tongue",
            "yellow"
        )
        val emojis = emojiNames.associateBy({
            val char = '\uB000' + emojiNames.indexOf(it) // Font & list are sorted alphabetically so we can do this
            Component.literal(char.toString()).withStyle(Style.EMPTY.withFont(ResourceLocation("island", "emojis")))
        }, { ":$it:" })

        emojis.forEach(::createEmojiButton)
    }


}