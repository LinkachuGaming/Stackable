package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

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

    // D:
    @Overwrite
    default int getMaxStackSize()
    {
        return StackableMod.MAX_STACK; //We ignore the original, we could do a check to ensure it was 64, however, this should always be 64, this is the base case.
    }


}
