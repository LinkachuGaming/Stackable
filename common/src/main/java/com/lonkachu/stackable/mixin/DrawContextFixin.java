package com.lonkachu.stackable.mixin;

import com.lonkachu.stackable.StackableMod;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public class DrawContextFixin
{
    @Shadow @Final private Matrix3x2fStack pose;

    /**
     * author Lonkachu
     * This code is a band-aid over the rendering issues that come from extending stack sizes above 999 as the text will start to creep onto other parts of the block
     * ModifyVariable is the best bet for this section as this method just so happens to include a string that is only used if the block text is not overwriten, extremely convienent
     */

    @ModifyVariable(method = "itemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private String modifyString(String value, Font textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride)
    {
        int count = stack.getCount();
        if (count <= 1)
        {
            return "";
        }
        if (StackableMod.GetConfig().CanTruncateItemCount())
        {
            int suffix = (int)Math.log10(count) / 3;
            count /= (int) Math.pow(1000, suffix);
            return count + StackableMod.SUFFIXES[suffix];
        }
        else
        {
            return String.valueOf(count);
        }
    }

    @Unique
    private float scale(String s)
    {
        if (s.length() <= 2)
        {
            return 1.0f;
        }

        return 2.5f / s.length();
    }

    @Redirect(method = "itemCount", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;width(Ljava/lang/String;)I"), remap = false)
    private int width(Font instance, String s)
    {
        float f = scale(s);
        return (int)(instance.width(s) * f);
    }
    @Inject(method = "itemCount", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V"), remap = false )
    private void width(Font font, ItemStack stack, int x, int y, String text, CallbackInfo ci)
    {
        String s = text == null ? String.valueOf(stack.getCount()) : text;
        float f = scale(s);
        this.pose.translate(x * (1 - f), y * (1 - f) + (1 - f) * 16);
        this.pose.scale(f);
    }

    /*
        @Redirect(method = "itemCount", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;width(Ljava/lang/String;)I"))
    private int width(Font instance, String s)
    {
        float f = scale(s);
        return (int)(instance.width(s) * f);
    }
    @Inject(method = "itemCount", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V") )
    private void width(Font font, ItemStack stack, int x, int y, String text, CallbackInfo ci)
    {
        String s = text == null ? String.valueOf(stack.getCount()) : text;
        float f = scale(s);
        this.pose.translate(x * (1 - f), y * (1 - f) + (1 - f) * 16);
        this.pose.scale(f);
    }
     */
}