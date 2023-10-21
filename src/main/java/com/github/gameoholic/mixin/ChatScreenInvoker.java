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


//    @Inject(at = @At("HEAD"), method = "insertText(Ljava/lang/String;Z)V")
//    private void insertText(CallbackInfo info) {
//        Minecraft mc = Minecraft.getInstance();
//        mc.gui.setOverlayMessage(Component.literal("test"), true);
//    }