package com.lonkachu.mixin;

import com.lonkachu.StackableMod;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.Clearable;
import org.spongepowered.asm.mixin.*;

@Mixin(Inventory.class)
public interface InventoryStackMixin extends Clearable {




    /**
     * @author Lonk
     * @reason effectively, I need to change this hardcoded return 64, to a return 128 (Preferably some configurable value)
     */
    @Overwrite
    default int getMaxCountPerStack() {
        return StackableMod.getMaxStackCount();
    }

    @Shadow
    int MAX_COUNT_PER_STACK = StackableMod.getMaxStackCount();


}
