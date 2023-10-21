package com.github.gameoholic.mixin;

import com.github.gameoholic.Archipelago;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.login.ClientboundGameProfilePacket;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)

public class LeaveIslandMixin {
    @Inject(at = @At("HEAD"), method = "handleDisconnection()V")
    private void handleDisconnection(CallbackInfo info) {
        Archipelago.INSTANCE.setConnectedToIsland(false);
        System.out.println("Disconnected from Island"); // todo: actual logging
    }
}
