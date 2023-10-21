package com.github.gameoholic.mixin;

import com.github.gameoholic.Archipelago;
import com.github.gameoholic.emoji.EmojiDialogUtil;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.protocol.login.ClientboundGameProfilePacket;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientHandshakePacketListenerImpl.class)
public class JoinIslandMixin {
    @Shadow @Final @Nullable private ServerData serverData;
    @Inject(at = @At("HEAD"), method = "handleGameProfile(Lnet/minecraft/network/protocol/login/ClientboundGameProfilePacket;)V")
    private void handleGameProfile(ClientboundGameProfilePacket clientboundGameProfilePacket, CallbackInfo info) {
        if (this.serverData == null) return;
        if (Archipelago.INSTANCE.getISLAND_IP_KEYWORDS().stream().anyMatch(
            IPKeyword -> serverData.ip.contains(IPKeyword))
        ) {
            System.out.println("Connected to Island on IP " + serverData.ip + "."); // todo: actual logging
            Archipelago.INSTANCE.setConnectedToIsland(true);
        }
    }
}
