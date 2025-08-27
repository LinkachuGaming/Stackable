package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.component.DataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(DataComponents.class)
public class DataComponentCommonFixin {

    @ModifyExpressionValue
            (
                    method = "<clinit>",
                    at = @At(value = "CONSTANT", args = "intValue=64")
            )
    private static int getMaxCountPerStack(int original) {
        return StackableMod.getMaxStackCount();
    }

}
