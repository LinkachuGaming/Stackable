package com.lonkachu.mixin.client;

import com.lonkachu.NumberUtils;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DrawContext.class)
public class DrawContextFixin
{
    @Shadow
    @Final
    private MatrixStack matrices;

    @Redirect(method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At (value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;getWidth(Ljava/lang/String;)I"))
    private int width(TextRenderer renderer, String text) {
        return (int) (renderer.getWidth(text) * NumberUtils.getScale(text));
    }


    @Inject(method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At (value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V", shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void rescaleText(TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride, CallbackInfo ci, String string) {
        float f = NumberUtils.getScale(string);
        if (f != 1f)
        {
            this.matrices.translate(x * (1 - f), y * (1 - f) + (1 - f) * 16, 0);
            this.matrices.scale(f, f, f);
        }
    }


    /**
     * @author Lonkachu
     * This code is a band-aid over the rendering issues that come from extending stack sizes above 999 as the text will start to creep onto other parts of the block,
     * I do want to at some point replace this with auto resizing text, maybe for next rewrite.
     * ModifyVariable is the best bet for this section as this method just so happens to include a string that is only used if the block text is not overwriten, extremely convienent
     */
    @ModifyVariable(
            method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true)
    private String modifyString(String value, TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride)
    {
        int count = stack.getCount();
        if (count == 1)
            return null;

        return NumberUtils.abberivate(count);
    }
}
