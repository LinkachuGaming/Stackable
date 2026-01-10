package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import net.minecraft.world.item.BundleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BundleItem.class)
public class BundleItemFixin {

    //@appendTooltip
    //We need to remove the 0/64 tooltip from the bundle and replace it with a tooltip that includes the actual stacksize we defined already
    @ModifyConstant(
            method = "appendHoverText",
            constant = @Constant(intValue = 64)
    )
    private int appendTooltip(int constant)
    {
        return StackableMod.getMaxStackCount();
    }

}
