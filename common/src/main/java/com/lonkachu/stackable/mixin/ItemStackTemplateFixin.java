package com.lonkachu.stackable.mixin;

/*
@ItemStackTemplateFixin
Author: Lonk
This mixin class exists to unpatch another codec that was changed to be limited to 99 in 26.1
 */

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.stackable.StackableMod;
import com.mojang.serialization.Codec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStackTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStackTemplate.class)
public class ItemStackTemplateFixin {
    @ModifyExpressionValue
            (
                    method = "lambda$static$0(Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;)Lcom/mojang/datafixers/kinds/App;", //This method is a Lambda, they aren't funda.
                    at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;")
            )
    private static Codec<Integer> replaceCodec(Codec<Integer> original)
    {
        return ExtraCodecs.intRange(0, StackableMod.MAX_STACK);
    }
}
