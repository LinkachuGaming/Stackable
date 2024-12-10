package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.StackableMod;
import com.mojang.serialization.Codec;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/*
@ItemStackFixin
Author: Lonk
This mixin class exists to unpatch a crash that occurs if a block stack is above 99, I'm not quite sure why mojang added this
tbh, it doesn't massively impact how people
 */
@Mixin(ItemStack.class)
public class ItemStackFixin {
    //1.2.2 - This is a lot cleaner and less crash prone. This was done to fix an issue with kubeJS, it might be worth making a PR for them since this should achieve the same effect and prevent other mods from crashing.
    @ModifyExpressionValue
            (
                    method = "method_57371", //This method is a Lambda, they aren't funda.
                    at = @At(value = "INVOKE", target = "Lnet/minecraft/util/dynamic/Codecs;rangedInt(II)Lcom/mojang/serialization/Codec;")
            )
    private static Codec<Integer> replaceCodec(Codec<Integer> original)
    {
        return Codec.intRange(0, StackableMod.MAX_STACK);
    }
}
