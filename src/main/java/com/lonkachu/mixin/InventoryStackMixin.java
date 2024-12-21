package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.lonkachu.StackableMod;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Clearable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Inventory.class)
public interface InventoryStackMixin extends Clearable {




    /**
     * @author Lonk
     *
     * This needs to change the constant of 99 into the variable we want to replace as the maximum stack size,
     * technically, this might be best described as just 2.147B, but I'm unsure if this makes a significant distance
     * This rewrite is to stop us from requiring overwrites and inject and early returns that are ultimately bad for
     * mod compatibility.
     */

    // 1.2.2 - Lonk - This rewrite allows multiple mods to inject into this without throwing an error.
    @ModifyReturnValue
            (
                    method = "getMaxCountPerStack",
                    at = @At("RETURN")
            )
    default int getMaxCountPerStack(int constant)
    {
        if (constant != 99)
        {
            return constant;
        }
        return StackableMod.MAX_STACK; //We ignore the original, we could do a check to ensure it was 64, however, this should always be 64, this is the base case.
    }


}
