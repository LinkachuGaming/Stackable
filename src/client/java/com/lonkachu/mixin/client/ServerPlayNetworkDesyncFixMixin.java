package com.lonkachu.mixin.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.EntityTrackingListener;
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
public class ServerPlayNetworkDesyncFixMixin implements EntityTrackingListener, TickablePacketListener, ServerPlayPacketListener {

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onCreativeInventoryAction", at = @At ("TAIL"))
    public void onCreativeInventoryAction(CreativeInventoryActionC2SPacket packet, CallbackInfo ci) {
        ItemStack itemStack = packet.getItemStack();
        boolean isValid = itemStack.isEmpty() || itemStack.getDamage() >= 0 && itemStack.getCount() <= itemStack.getMaxCount() && !itemStack.isEmpty();
        boolean bl2 = packet.getSlot() >= 1 && packet.getSlot() <= 45; //I'm not smart enough to understand exactly what this code does,

        if(isValid && bl2) {
            this.player.playerScreenHandler.getSlot(packet.getSlot()).setStack(itemStack);
            this.player.playerScreenHandler.sendContentUpdates();
        }
    }

    @Shadow
    public ServerPlayerEntity getPlayer() {
        return null;
    }

    @Shadow
    public void sendPacket(Packet<?> packet) {

    }

    @Shadow
    public void tick() {

    }

    @Shadow
    public void onDisconnected(Text reason) {

    }

    @Shadow
    public boolean isConnectionOpen() {
        return false;
    }

    @Shadow
    public void onHandSwing(HandSwingC2SPacket packet) {

    }

    @Shadow
    public void onChatMessage(ChatMessageC2SPacket packet) {

    }

    @Shadow
    public void onCommandExecution(CommandExecutionC2SPacket packet) {

    }

    @Shadow
    public void onMessageAcknowledgment(MessageAcknowledgmentC2SPacket packet) {

    }

    @Shadow
    public void onClientStatus(ClientStatusC2SPacket packet) {

    }

    @Shadow
    public void onClientSettings(ClientSettingsC2SPacket packet) {

    }

    @Shadow
    public void onButtonClick(ButtonClickC2SPacket packet) {

    }

    @Shadow
    public void onClickSlot(ClickSlotC2SPacket packet) {

    }

    @Shadow
    public void onCraftRequest(CraftRequestC2SPacket packet) {

    }

    @Shadow
    public void onCloseHandledScreen(CloseHandledScreenC2SPacket packet) {

    }

    @Shadow
    public void onCustomPayload(CustomPayloadC2SPacket packet) {

    }

    @Shadow
    public void onPlayerInteractEntity(PlayerInteractEntityC2SPacket packet) {

    }

    @Shadow
    public void onKeepAlive(KeepAliveC2SPacket packet) {

    }

    @Shadow
    public void onPlayerMove(PlayerMoveC2SPacket packet) {

    }

    @Shadow
    public void onPong(PlayPongC2SPacket packet) {

    }

    @Shadow
    public void onUpdatePlayerAbilities(UpdatePlayerAbilitiesC2SPacket packet) {

    }

    @Shadow
    public void onPlayerAction(PlayerActionC2SPacket packet) {

    }

    @Shadow
    public void onClientCommand(ClientCommandC2SPacket packet) {

    }

    @Shadow
    public void onPlayerInput(PlayerInputC2SPacket packet) {

    }

    @Shadow
    public void onUpdateSelectedSlot(UpdateSelectedSlotC2SPacket packet) {

    }

    @Shadow
    public void onCreativeInventoryAction(CreativeInventoryActionC2SPacket packet) {

    }

    @Shadow
    public void onUpdateSign(UpdateSignC2SPacket packet) {

    }

    @Shadow
    public void onPlayerInteractBlock(PlayerInteractBlockC2SPacket packet) {

    }

    @Shadow
    public void onPlayerInteractItem(PlayerInteractItemC2SPacket packet) {

    }

    @Shadow
    public void onSpectatorTeleport(SpectatorTeleportC2SPacket packet) {

    }

    @Shadow
    public void onResourcePackStatus(ResourcePackStatusC2SPacket packet) {

    }

    @Shadow
    public void onBoatPaddleState(BoatPaddleStateC2SPacket packet) {

    }

    @Shadow
    public void onVehicleMove(VehicleMoveC2SPacket packet) {

    }

    @Shadow
    public void onTeleportConfirm(TeleportConfirmC2SPacket packet) {

    }

    @Shadow
    public void onRecipeBookData(RecipeBookDataC2SPacket packet) {

    }

    @Shadow
    public void onRecipeCategoryOptions(RecipeCategoryOptionsC2SPacket packet) {

    }

    @Shadow
    public void onAdvancementTab(AdvancementTabC2SPacket packet) {

    }

    @Shadow
    public void onRequestCommandCompletions(RequestCommandCompletionsC2SPacket packet) {

    }

    @Shadow
    public void onUpdateCommandBlock(UpdateCommandBlockC2SPacket packet) {

    }

    @Shadow
    public void onUpdateCommandBlockMinecart(UpdateCommandBlockMinecartC2SPacket packet) {

    }

    @Shadow
    public void onPickFromInventory(PickFromInventoryC2SPacket packet) {

    }

    @Shadow
    public void onRenameItem(RenameItemC2SPacket packet) {

    }

    @Shadow
    public void onUpdateBeacon(UpdateBeaconC2SPacket packet) {

    }

    @Shadow
    public void onUpdateStructureBlock(UpdateStructureBlockC2SPacket packet) {

    }

    @Shadow
    public void onSelectMerchantTrade(SelectMerchantTradeC2SPacket packet) {

    }

    @Shadow
    public void onBookUpdate(BookUpdateC2SPacket packet) {

    }

    @Shadow
    public void onQueryEntityNbt(QueryEntityNbtC2SPacket packet) {

    }

    @Shadow
    public void onQueryBlockNbt(QueryBlockNbtC2SPacket packet) {

    }

    @Shadow
    public void onUpdateJigsaw(UpdateJigsawC2SPacket packet) {

    }

    @Shadow
    public void onJigsawGenerating(JigsawGeneratingC2SPacket packet) {

    }

    @Shadow
    public void onUpdateDifficulty(UpdateDifficultyC2SPacket packet) {

    }

    @Shadow
    public void onUpdateDifficultyLock(UpdateDifficultyLockC2SPacket packet) {

    }

    @Shadow
    public void onPlayerSession(PlayerSessionC2SPacket packet) {

    }
}
