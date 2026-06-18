# Stackable 2.1.1!

a port to 1.21.1!

* Changes since 2.0.4
  * When storing items in bundles, any non-overriden stackable item will now have a penalty multiplier of 8.
    * This means each item will count as 8 when in a bundle, and the max bundle size is now 128 by default
    * This is configurable in stackable.json with the "bundleStackPenalty" property.
* The Debug log present in Forge and NeoForge has been removed from 1.21.1 branch