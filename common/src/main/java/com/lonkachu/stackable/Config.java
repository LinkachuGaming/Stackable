package com.lonkachu.stackable;

import java.util.ArrayList;

public class Config {

    private int maxStack;

    private boolean truncateItemCount;
    private ArrayList<StacksizeOverride> StacksizeOverride = new ArrayList<>();
    public Config(int maxCount) {
        maxStack = maxCount;
        truncateItemCount = false;
        StacksizeOverride.add(new StacksizeOverride("minecraft:egg", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:brown_egg", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:blue_egg", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:snowball", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:oak_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:pale_oak_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:spruce_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:birch_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:jungle_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:acacia_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:dark_oak_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:crimson_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:warped_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:mangrove_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:cherry_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:bamboo_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:oak_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:pale_oak_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:spruce_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:birch_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:jungle_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:acacia_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:dark_oak_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:crimson_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:warped_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:mangrove_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:cherry_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:bamboo_hanging_sign", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:honey_bottle", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:white_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:orange_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:magenta_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:light_blue_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:yellow_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:lime_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:pink_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:gray_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:light_gray_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:cyan_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:purple_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:blue_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:brown_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:green_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:red_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:black_banner", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:writable_book", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:ender_pearl", maxCount / 4));
        StacksizeOverride.add(new StacksizeOverride("minecraft:armor_stand", maxCount / 4));
    }

    public int getMaxStackSize() {
        return maxStack;
    }
    public boolean CanTruncateItemCount()
    {
        return truncateItemCount;
    }
    public ArrayList<StacksizeOverride> GetOverrides() { return StacksizeOverride; }
}
