package com.lonkachu.mixin.client;

import com.lonkachu.StackableMod;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.Settings.class)
public abstract class ItemSettingsMixinClient {


    @Shadow int maxCount;
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo ci) {
        // This code is injected into the start of MinecraftServer.loadWorld()V
        this.maxCount = 127;
        //ExampleMod.LOGGER.info("Hello Mixin world!");
    }


}
