package com.lonkachu.mixin;

import com.lonkachu.StackableMod;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.Settings.class)
public abstract class ItemSettingsMixin {


	@Shadow int maxCount;
	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo ci) {

		this.maxCount = 127;

	}


}
