package com.lonkachu.stackable.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public class DrawContextFixin
{
    /**
     * @author Lonkachu
     * This code is a band-aid over the rendering issues that come from extending stack sizes above 999 as the text will start to creep onto other parts of the block,
     * I do want to at some point replace this with auto resizing text, maybe for next rewrite.
     * ModifyVariable is the best bet for this section as this method just so happens to include a string that is only used if the block text is not overwriten, extremely convienent
     */

    @ModifyVariable(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private String modifyString(String value, Font textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride)
    {
        int count = stack.getCount();
        if(count > 999999999) {
            count /= 1000000000;
            return count + "B";
        }
        else if (count > 999999)
        {
            count /= 1000000;
            return count + "M";
        }
        else if (count > 999)
        {
            count /= 1000;
            return count + "K";
        }
        else if (count > 1)
        {
            return String.valueOf(count);
        }


        return "";
    }
}