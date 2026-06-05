package com.lonkachu.stackable;

import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.SharedConstants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
I Intend to complete rewrite tests soon, as a lot of minecraft functionality breaks unless you use GameTests, but I really just want to get the release done now.
So unfortunately, some tests are disabled.
 */

public class MixinTest
{
    @BeforeAll
    static void BeforeAll()
    {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

    }

    @Test
    public void TestBundleSize()
    {
        Assertions.assertNotEquals(BundleItem.DEFAULT_MAX_STACK_SIZE, StackableMod.getMaxStackCount());
    }

    @Test
    public void TestStackSizeComponentFixin()
    {
        Assertions.assertEquals(StackableMod.getMaxStackCount(), DataComponents.COMMON_ITEM_COMPONENTS.get(DataComponents.MAX_STACK_SIZE));
    }
}
