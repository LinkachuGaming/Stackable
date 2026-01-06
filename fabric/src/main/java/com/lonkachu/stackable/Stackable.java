package com.lonkachu.stackable;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.lang.ref.Reference;
import java.util.Optional;
import java.util.Stack;

public class Stackable implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        StackableMod.init();

        for(StacksizeOverride override : StackableMod.GetConfig().GetOverrides()) {
            Optional<Holder.Reference<Item>> optionalitem = BuiltInRegistries.ITEM.get(override.GetIdentifier());

            if (optionalitem.isEmpty())
            {
                StackableMod.LOGGER.error("No such block exists with the key " + override.GetIdentifier() + " skipping stack size modification!!!");
                continue;
            }
            Item item = optionalitem.get().value();
            DefaultItemComponentEvents.MODIFY.register(modifyContext ->
                    {
                        modifyContext.modify(item, builder ->
                                {
                                    builder.set(DataComponents.MAX_STACK_SIZE, override.GetCount());
                                }
                        );
                    }
            );
        }
    }
}
