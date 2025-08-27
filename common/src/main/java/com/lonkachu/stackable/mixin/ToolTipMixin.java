package com.lonkachu.stackable.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * @author fmp
 */
@Mixin(ItemStack.class)
public abstract class ToolTipMixin {
    /**
     * Adds the full count in item tooltip.
     * @author Devin-Kerman from stacc, updated for 1.21
     */
    @Inject(method = "getTooltipLines", at = @At("RETURN"))
    private void addOverflowTooltip(Item.TooltipContext tooltipContext, Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> cir) {
        if (this.getCount() > 999) {
            List<Component> texts = cir.getReturnValue();
            texts.add(1, Component.literal(String.valueOf(this.getCount())).withStyle(ChatFormatting.GRAY));
        }
    }

    @Shadow
    public abstract int getCount();
}
