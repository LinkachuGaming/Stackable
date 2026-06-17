package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(DataComponents.class)
public class DataComponentCommonFixin {

    @ModifyExpressionValue
            (
                    method = "<clinit>",
                    at = @At(value = "CONSTANT", args = "intValue=64")
            )
    private static int getMaxCountPerStack(int original) {
        return StackableMod.getMaxStackCount();
    }

    @SuppressWarnings("UnresolvedMixinReference") //Obviously one of these mixins will be unresolved, as we are specifying both Mojmaps and Fabric intermediary
    @ModifyExpressionValue(
            method = {
                    "lambda$static$1(Lnet/minecraft/core/component/DataComponentType$Builder;)Lnet/minecraft/core/component/DataComponentType$Builder;",
                    "method_58570"
            },
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;"),
            require = 1
    )
    private static Codec<Integer> replaceCodec(Codec<Integer> original)
    {
        return Codec.intRange(0, StackableMod.MAX_STACK);
    }
}
