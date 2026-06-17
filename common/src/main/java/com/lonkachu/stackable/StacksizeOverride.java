package com.lonkachu.stackable;

import net.minecraft.resources.Identifier;
public class StacksizeOverride {
    private String namespaceID;
    private int value;
    public StacksizeOverride(String namespaceID, int value)
    {
        this.namespaceID = namespaceID;
        this.value = value;
    }


    public Identifier GetIdentifier()
    {
        return Identifier.bySeparator(namespaceID, ':');
    }
    public int GetCount() { return value; }
}
