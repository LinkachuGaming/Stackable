# Welcome to Stackable 2.0.3!

Who would've guessed, a 1.21.1 backport. Considering minecraft's new update strategy, and the many changes to my dev pipeline
since 1.21 making supporting more versions practical; I decided to finally push an update for my 1.21.1 and bring the entire new
featureset from my latest builds.

Since 1.3:

+ Multiplayer fixes
+ Stacksizes properly merge 
+ Several rewrites which should improve stability,

2.0.3 Changes:

+ Items stacks over 999 will now display without being rounded.
+ Item stacks will scale down as more digits are added
  + There is an option to return to the old setup via the configuration file.
+ Default stacksize is now 1024!
  + This will not affect existing worlds, but this is being done to hopefully stop people from thinking there is still a hard cap.
+ Fixed an issue with Forge crashing on startup.