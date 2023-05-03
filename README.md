# Stackable

Stackable is a mod that increases all block stacking limits to any value less than 127 for minecraft 1.19.4.

# Why 127?

Effectively, I'm lazy, but in reality, minecraft stores all items with an single signed byte which for our purposes only can be bewteen -128 to 127, since negative values are invalid that leaves a maximum stack size of 127. Moreover, this mod is intended to be compatible with other mods which may also use signed bytes to store items.

# Why make this mod
I don't know, other mods already exist that allow for stack sizes above 127. This was more for experimentation with creating mixins and understanding minecrafts code a little better.

# Will you update to X?
Maybe.

# Will you backport to X?
Eh, not really.

# There's a bug here?
I might fix bugs that are Vanilla specfic, but mod compatibility is gonna be kinda hard to do 
