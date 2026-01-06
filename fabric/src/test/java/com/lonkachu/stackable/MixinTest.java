package com.lonkachu.stackable;

import net.minecraft.SharedConstants;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MixinTest
{
    @BeforeAll
    static void BeforeAll()
    {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }
    @Test
    public void TestDefaultStackSize()
    {
        Assertions.assertNotEquals(64, Blocks.DIAMOND_BLOCK.asItem().getDefaultMaxStackSize());
    }
    @Test
    public void TestVanillaMaxStackSize()
    {
        Assertions.assertNotEquals(99, Blocks.DIAMOND_BLOCK.asItem().getDefaultMaxStackSize());
    }
    @Test
    public void TestBundleSize()
    {
        Assertions.assertNotEquals(BundleItem.DEFAULT_MAX_STACK_SIZE, StackableMod.getMaxStackCount());
    }

    @Test
    public void TestMerge()
    {
        ItemStack hundredBrick = new ItemStack(Items.BRICK, 100);
        ItemStack twoHundredBrick = new ItemStack(Items.BRICK, 200);

        ItemStack item = ItemEntity.merge(hundredBrick, twoHundredBrick, 300);

        Assertions.assertEquals(128, item.getCount());
    }

    @Test
    public void TestStackSizeComponentFixin()
    {
        Assertions.assertEquals(StackableMod.getMaxStackCount(), DataComponents.COMMON_ITEM_COMPONENTS.get(DataComponents.MAX_STACK_SIZE));
    }
}
