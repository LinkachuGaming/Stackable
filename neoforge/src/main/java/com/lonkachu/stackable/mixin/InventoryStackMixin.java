package com.lonkachu.stackable.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.lonkachu.stackable.StackableMod;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Container.class)
public interface InventoryStackMixin {
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
                    method = "getMaxStackSize()I",
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
