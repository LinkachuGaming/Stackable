package com.lonkachu.stackable;

import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StackTest {
    @BeforeAll
    static void BeforeAll()
    {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    @Test
    public void TestStackSizeOverrideReturnBlock()
    {
        StacksizeOverride override = new StacksizeOverride("minecraft:dirt", 999);

        Block expected = Blocks.DIRT;
        Block actual = BuiltInRegistries.BLOCK.get(override.GetIdentifier());
        Assertions.assertNotSame(BuiltInRegistries.BLOCK.get(override.GetIdentifier()).toString(), Blocks.DIRT.toString());
    }
}
