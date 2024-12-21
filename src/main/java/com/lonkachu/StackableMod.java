package com.lonkachu;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class StackableMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("stackable");

    public static final int MAX_STACK = 2147483647;
    public static final int DEFAULT_STACK = 128;

    private static int maxStack = -1;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        Config config;
        try {
            config = configLoader.bootstrapConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        maxStack = config.getMaxStackSize();
        if(maxStack > MAX_STACK) {
            maxStack = MAX_STACK; //We don't wanna let players overflow this
        }
        test t = new test();
        t.logStackSize();
        LOGGER.info("Configured to: " + maxStack);



        for(StacksizeOverride override : config.GetOverrides())
        {
            Item item = Registries.ITEM.get(override.GetIdentifier());

            DefaultItemComponentEvents.MODIFY.register(modifyContext ->
                    {
                        modifyContext.modify(item, builder ->
                                {
                                    builder.add(DataComponentTypes.MAX_STACK_SIZE, override.GetCount());
                                }
                        );
                    }
            );
        }
    }

    public static int getMaxStackCount()
    {
        if(maxStack == -1) //if we call getMax before we have initalized everything, read the config
        {
            Config config;
            try {
                config = configLoader.bootstrapConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            maxStack = config.getMaxStackSize();
        }
        return maxStack;
    }

}