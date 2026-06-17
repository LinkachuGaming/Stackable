package com.lonkachu.stackable;


import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

import java.util.NoSuchElementException;

@Mod(Constants.MOD_ID)
public class Stackable {

    public Stackable(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        StackableMod.init();
    }

    @SubscribeEvent
    public void ModifyDefaultComponentsEvent(ModifyDefaultComponentsEvent event)
    {
        try
        {
            //TODO: This function is deprecated
            event.modifyMatching(
                    item -> !item.isDamageable(item.getDefaultInstance()) && item.getDefaultMaxStackSize() == 64, //Basically, we want to ignore any damagable item, and also ensure the object has the default stack size.
                    builder -> builder.set(DataComponents.MAX_STACK_SIZE, StackableMod.getMaxStackCount())
            );
        } catch (IllegalStateException e){
            throw new RuntimeException(e);
        }

        for (StacksizeOverride override : StackableMod.GetConfig().GetOverrides())
        {
            try
            {
                Item item = BuiltInRegistries.ITEM.get(override.GetIdentifier());
                event.modify(item, builder -> builder.set(DataComponents.MAX_STACK_SIZE, override.GetCount()));
            } catch (NoSuchElementException e)
            {
                StackableMod.LOGGER.error("No such block exists with the key " + override.GetIdentifier() + " skipping stack size modification!!!");
            }
        }
    }
}