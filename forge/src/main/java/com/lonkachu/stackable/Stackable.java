package com.lonkachu.stackable;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.GatherComponentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Optional;

@Mod(Constants.MOD_ID)
public class Stackable {

    public Stackable(FMLJavaModLoadingContext ctx) {

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        StackableMod.init();


        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(this::RegistryEvent);
    }
    public void RegistryEvent(GatherComponentsEvent.Item event)
    {
        Item i = event.getOwner();
        StackableMod.LOGGER.info("FIRE!!!");

        for (StacksizeOverride override : StackableMod.GetConfig().GetOverrides())
        {
            Optional<Holder.Reference<Item>> optional = BuiltInRegistries.ITEM.get(override.GetIdentifier());

            if (optional.isEmpty())
            {
                StackableMod.LOGGER.error("No such block exists with the key " + override.GetIdentifier() + " skipping stack size modification!!!");
                continue;
            }
            Item p = optional.get().value();

            if (i == p)
            {
                event.register(DataComponents.MAX_STACK_SIZE, override.GetCount());
            }
        }
    }
}