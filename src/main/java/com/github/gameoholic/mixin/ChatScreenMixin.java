package com.github.gameoholic.mixin;

import com.github.gameoholic.emoji.EmojiDialogUtil;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        EmojiDialogUtil.INSTANCE.openDialog();
    }

    @Inject(at = @At("HEAD"), method = "removed()V")
    private void removed(CallbackInfo info) {
        EmojiDialogUtil.INSTANCE.closeDialog();
    }
}


