package com.github.gameoholic.mixin;

import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChatScreen.class)
public interface ChatScreenInvoker {
    /**
     * @param string The string to insert/set
     * @param bl True if set string, false if insert string
     */
    @Invoker("insertText")
    void invokeInsertText(String string, boolean bl);

}


