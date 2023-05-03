package com.lonkachu;

import net.fabricmc.api.ClientModInitializer;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //I don't actually need to do anything on the client for this mod.
        //We should only really need the mixins to be done serverside, however, I don't know if MC does any calculations using these values on client, so it's best to keep
    }
}