package com.lonkachu.stackable.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.lonkachu.stackable.StackableMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/*
@BundleContentsMixin
Author: Lonk
This is balance patch I decided to do, I didn't really like that bundles effectively gave you infinite inventory, so I added
a customizable penalty system, where each item counts as multiple, by default this limits bundles to 128 items.
 */

@Mixin(BundleContents.class)
public class BundleContentsMixin
{
    @ModifyExpressionValue(method = "getWeight",
    at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/math/Fraction;getFraction(II)Lorg/apache/commons/lang3/math/Fraction;"))
    private static Fraction GetFraction(Fraction original, @Local(ordinal = 0, argsOnly = true) ItemInstance item)
    {
        return StackableMod.GetConfig().getMaxStackSize() == item.getMaxStackSize() ?
                Fraction.getFraction(StackableMod.GetConfig().getBundleStackPenalty(), item.getMaxStackSize()) :
                Fraction.getFraction(1, item.getMaxStackSize());
    }
}
