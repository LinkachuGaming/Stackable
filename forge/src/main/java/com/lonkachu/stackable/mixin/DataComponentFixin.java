package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(DataComponents.class)
public class DataComponentFixin {
    @ModifyExpressionValue
            (
                    method = "lambda$static$1(Lnet/minecraft/core/component/DataComponentType$Builder;)Lnet/minecraft/core/component/DataComponentType$Builder;",
                    at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;")
            )
    private static Codec<Integer> replaceCodec(Codec<Integer> original)
    {
        return Codec.intRange(0, StackableMod.MAX_STACK);
    }
}
