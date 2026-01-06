package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Container.class)
public interface InventoryStackMixinFallback extends Clearable, Iterable<ItemStack> {
    /**
     * @author Lonk
     *
     * This needs to change the constant of 99 into the variable we want to replace as the maximum stack size.
     *
     * This class is a fallback due to Forge using an sponge mixin rather than fabric mixin, it cannot support the proper injection,
     * if you are having an incompatibility due to this mixin, unfortunately there isn't much I can do. But really, who else is overwriting getNaxStackSize....
     */

    // D:
    @Overwrite
    default int getMaxStackSize()
    {
        return StackableMod.MAX_STACK; //We ignore the original, we could do a check to ensure it was 64, however, this should always be 64, this is the base case.
    }
}
