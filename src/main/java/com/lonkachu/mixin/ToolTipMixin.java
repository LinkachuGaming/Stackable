package com.lonkachu.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addOverflowTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir) {
        if (this.getCount() > 999) {
            List<Text> texts = cir.getReturnValue();
            texts.add(1, Text.literal(String.valueOf(this.getCount())).formatted(Formatting.GRAY));
        }
    }

    @Shadow
    public abstract int getCount();
}
