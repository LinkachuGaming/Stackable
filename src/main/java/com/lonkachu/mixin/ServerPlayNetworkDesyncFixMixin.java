package com.lonkachu.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.server.network.PlayerAssociatedNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
I wasn't the person who discovered this desync bug within minecraft, infact this bug appears to be found by Devan Kerman in his CC0 repository for stacc, the fix and code provided is original however

Effectively, this class fixes the onCreativeInventoryAction method which by default checks if the itemcount of the new stack is less than 64 rather then using the inbuilt getMaxCount variable this should have the benefit of preventing overstacking on items that stack to less then 64 to begin with
 */

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkDesyncFixMixin implements ServerPlayPacketListener,
        PlayerAssociatedNetworkHandler,
        TickablePacketListener {

    @Shadow private int creativeItemDropThreshold;
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onCreativeInventoryAction", at = @At ("TAIL"))
    public void onCreativeInventoryAction(CreativeInventoryActionC2SPacket packet, CallbackInfo ci) {
        ItemStack itemStack = packet.getStack();
        boolean slotIsPositive = packet.getSlot() < 0;
        boolean isValid = itemStack.isEmpty() || itemStack.getDamage() >= 0 && itemStack.getCount() <= itemStack.getMaxCount() && !itemStack.isEmpty();
        boolean bl2 = packet.getSlot() >= 1 && packet.getSlot() <= 45; //I'm not smart enough to understand exactly what this code does,

        if(isValid && bl2) {
            this.player.playerScreenHandler.getSlot(packet.getSlot()).setStack(itemStack);
            this.player.playerScreenHandler.sendContentUpdates();
        } else if(slotIsPositive && isValid && this.creativeItemDropThreshold < 200) {
            this.creativeItemDropThreshold += 20;
            this.player.dropItem(itemStack, true);
        }
    }


}
