package com.lonkachu.stackable.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.stackable.StackableMod;
import com.mojang.serialization.Codec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/*
@ItemStackFixin
Author: Lonk
This mixin class exists to unpatch a crash that occurs if a block stack is above 99, I'm not quite sure why mojang added this
tbh, it doesn't massively impact how people
 */

@Mixin(ItemStack.class)
public class ItemStackFixin {
    //1.2.2 - This is a lot cleaner and less crash prone. This was done to fix an issue with kubeJS, it might be worth making a PR for them since this should achieve the same effect and prevent other mods from crashing.
    @SuppressWarnings("UnresolvedMixinReference") //Obviously one of these mixins will be unresolved, as we are specifying both Mojmaps and Fabric intermediary
    @ModifyExpressionValue
            (
                    method = {
                            "lambda$static$3(Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;)Lcom/mojang/datafixers/kinds/App;",
                            "method_57371",
                    }, //This method is a Lambda, they aren't funda.
                    at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;"),
                    require = 1
            )
    private static Codec<Integer> replaceCodec(Codec<Integer> original)
    {
        return ExtraCodecs.intRange(0, StackableMod.MAX_STACK);
    }
}
