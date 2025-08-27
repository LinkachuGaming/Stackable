package com.lonkachu.stackable;

import net.minecraft.resources.ResourceLocation;
public class StacksizeOverride {
    private String namespaceID;
    private int value;
    public StacksizeOverride(String namespaceID, int value)
    {
        this.namespaceID = namespaceID;
        this.value = value;
    }


    public ResourceLocation GetIdentifier()
    {
        return ResourceLocation.bySeparator(namespaceID, ':');
    }
    public int GetCount() { return value; }
}
