package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.StackableMod;
import com.mojang.serialization.Codec;
import net.minecraft.component.DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(DataComponentTypes.class)
public class DataComponentTypesFixin {

    @ModifyExpressionValue
            (
                    method = "<clinit>",
                    at = @At(value = "CONSTANT", args = "intValue=64")
            )
    private static int getMaxCountPerStack(int original) {
        return StackableMod.getMaxStackCount();
    }

    @ModifyExpressionValue
            (
                    method = "method_58570",
                    at = @At(value = "INVOKE", target = "Lnet/minecraft/util/dynamic/Codecs;rangedInt(II)Lcom/mojang/serialization/Codec;")
            )
    private static Codec<Integer> replaceCodec(Codec<Integer> original)
    {
        return Codec.intRange(0, StackableMod.MAX_STACK);
    }
//private static final ComponentMap DEFAULT_ITEM_COMPONENTS = ComponentMap.builder().add(MAX_STACK_SIZE, StackableMod.getMaxStackCount()).add(LORE, LoreComponent.DEFAULT).add(ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).add(REPAIR_COST, 0).add(ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT).add(RARITY, Rarity.COMMON).build();
}
