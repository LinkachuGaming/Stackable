package com.lonkachu.stackable.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.stackable.StackableMod;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.commands.ItemCommands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/*
@ItemCommandsFixin
Author: Lonk
Oddly enough, mojang added this 1,99 cap in 26.1? But I have no clue what this actually impacts, I know it has to do with stack size, so this patches
something, but what it does is unclear.
 */

@Mixin(ItemCommands.class)
public class ItemCommandsFixin
{
    @ModifyExpressionValue(
            method = "register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/commands/CommandBuildContext;)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/arguments/IntegerArgumentType;integer(II)Lcom/mojang/brigadier/arguments/IntegerArgumentType;")
    )
    private static IntegerArgumentType ReplaceIntegerArgType(IntegerArgumentType orginal)
    {
        return IntegerArgumentType.integer(1, StackableMod.MAX_STACK);
    }
}
