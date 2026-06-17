import java.lang.reflect.Method;
import java.util.Stack;

import com.lonkachu.stackable.StackableMod;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;

import org.apache.commons.lang3.math.Fraction;

public class StacksizeTest implements FabricGameTest {

    @GameTest(template = EMPTY_STRUCTURE)
    public void TestDefaultStackSize(GameTestHelper context) {
        ItemStack diamond = new ItemStack(Items.DIAMOND);
        int stackSize = diamond.getOrDefault(DataComponents.MAX_STACK_SIZE, -1);
        context.assertFalse(stackSize == 64, "DefaultStacksize is erroneously 64");
        context.succeed();
    }

    @GameTest(template = EMPTY_STRUCTURE)
    public void TestVanillaMaxStackSize(GameTestHelper context) {
        ItemStack diamond = new ItemStack(Items.DIAMOND);
        int stackSize = diamond.getOrDefault(DataComponents.MAX_STACK_SIZE, -1);
        context.assertFalse(stackSize == 99, "Actual max stacksize is erroneously 99");
        context.succeed();
    }

    @GameTest(template = EMPTY_STRUCTURE)
    public void TestBundles(GameTestHelper context) {
        var itemStack = new ItemStack(Items.BUNDLE);

        BundleContents.Mutable builder = new BundleContents.Mutable(BundleContents.EMPTY);

        for (int i = 0; i < StackableMod.getMaxStackCount() / StackableMod.GetConfig().getBundleStackPenalty(); i++) {
            var dirt = new ItemStack(Items.DIRT);
            var capacity = builder.weight();
            context.assertFalse(capacity.equals(Fraction.ONE), "Bundle filled early");
            builder.tryInsert(dirt);
        }

        var capacity = builder.weight();
        context.assertTrue(capacity.equals(Fraction.ONE), "Bundle never filled.");

        context.succeed();
    }

    //Ensure empty area.
    @GameTest(template = EMPTY_STRUCTURE)
    public void TestItemMerging(GameTestHelper context) {
        BlockPos spawnPoint = new BlockPos(1, 1, 1);
        var absPoint = context.absolutePos(spawnPoint);

        ItemEntity ie1 = new ItemEntity(context.getLevel(), absPoint.getX(), absPoint.getY(), absPoint.getZ(), new ItemStack(Items.DIAMOND, 256));
        ItemEntity ie2 = new ItemEntity(context.getLevel(), absPoint.getX(), absPoint.getY(), absPoint.getZ(), new ItemStack(Items.DIAMOND, 512));

        context.getLevel().addFreshEntity(ie1);
        context.getLevel().addFreshEntity(ie2);

        context.succeedWhen(() -> {
            var itemsInArea = context.getEntities(EntityType.ITEM, spawnPoint, 2);

            if (itemsInArea.size() == 1) {
                ItemEntity merged = itemsInArea.get(0);
                context.assertTrue(merged.getItem().getCount() == 768, "Did not merge entity itemstacks into the expected 768 items.");
            }
        });
    }

    @GameTest(template = EMPTY_STRUCTURE)
    public void TestItemSlotMerge(GameTestHelper context) {
        var player = context.makeMockPlayer(GameType.SURVIVAL);
        var menu = player.inventoryMenu;

        player.getInventory().clearContent();

        Slot slot1 = menu.getSlot(9); //Items 9 & 10 are apparently hotbar 1 & 2.
        Slot slot2 = menu.getSlot(10); // I may be wrong.

        slot1.set(new ItemStack(Items.DIAMOND, 512));
        slot2.set(new ItemStack(Items.DIAMOND, 256));

        context.startSequence()
                .thenExecute(() -> {
                    menu.clicked(9, 0, ClickType.PICKUP, player);
                })
                .thenIdle(1)
                .thenExecute(() -> {
                    menu.clicked(10, 0, ClickType.PICKUP, player);
                })
                .thenIdle(1)
                .thenExecute(() -> {
                    var finalItem = slot2.getItem();
                    context.assertTrue(finalItem.getCount() == 768, "Did not merge the item slots into the expected 768 items");
                })
                .thenSucceed();
    }
}