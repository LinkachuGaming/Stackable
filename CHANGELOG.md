# Stackable 2.1.0!

Some new functionality, plus a port to 26.1!

+ Ported to 26.1, future updates will be targeting 1.21.1 and 26.1, I intend to update 1.21.1 soon.
+ When storing items in bundles, any non-overriden stackable item will now have a penalty multiplier of 8.
  + This means each item will count as 8 when in a bundle, and the max bundle size is now 128 by default
  + This is configurable in stackable.json with the "bundleStackPenalty" property.
+ All mixins are now shared across both Neoforge and Fabric with the move to unobfuscated Minecraft versions.
+ Moved away from Parchment to better support snapshot/pre-release development, this should speed up ports.

P.S. Forge support will come later once Forge updates to 26.1.