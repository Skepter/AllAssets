AllAssets
========

A plugin which does everything a server would ever possibly need.
Hmm. a readme file. I should sort this out when I get the chance to.

What it is
----------
Pretty simply put - this plugin is aimed to be the 'best plugin a server could ever need'
It is designed with speed, simplicity and ease of use in mind, so even brand new server owners can get to grips with it.

Also designed to be as light weight as possible (compared to Essentials) and supposed to be really easy to use.

Compiling & source code info
----------------------------
Not allowed. You could do it if you want to, but I prefer you'd use the [AllAssets releases](https://github.com/Skepter/AllAssets/releases)

The source code is there, feel free to explore. Those who are new to source code browsing, here's the list of file information:
* .settings - Just some boring eclipse stuff. Nothing interesting here.
* resources - Some files I put there for reference when coding. Contains to do lists and future plans.
* src/io/github/skepter/allassets - Code here! Check this out - it's packed with useful stuff!
* .classpath - Eclipse settings. Nothing interesting.
* .project - Eclipse settings. Nothing interesting.
* pom.xml - Maven build script thingy.

About
-----
Skepter, the single developer of AllAssets hated Essentials. He hated how it was easy to exploit teleporting by using /tpahere on himself whilst standing on the corner of a block and teleporting to a location far below him (easy way to descend canyons without the descend permission xD). He hated the massive (and amount) of files required - Essentials, EssentialsSpawn/Protect/Chat etc. He hated the system of removing commands by opening the jar file and removing it from Essentials' plugin.yml. He planned on creating a new plugin which would be:
* The successor to his older plugin called NecessaryExtras
* A plugin that was better better than Essentials
* A more 'user friendly' plugin which would also have advanced features

Upon creation, he named his project AllInOne, until his friend told him that there was an [existing plugin by that name](http://dev.bukkit.org/bukkit-plugins/allinone/). Since then, after some help with friends (ajcozzo, Gerrie_K, amoniuszko20) he renamed his project to 'AllAssets'.
The most unique thing about this plugin is, most large plugins such as CommandBook and Essentials for example were normally created with a collaboration of developers, however AllAssets aims to be completed by a single developer.

Why choose me?
--------------
I know what you're thinking - why choose this plugin over [PLUGIN NAME]?
Well, this plugin is different. I'm not necessarily saying the commands - some are similar such as /god and /tp, however, this plugin has been designed with superior precision and lots of planning time. Unlike (most) others, this plugin can:
* tell you which players are op
* make you a ghost (translucent)
* remove unwanted commands (entirely - not 'disable' them, ACTUALLY remove them!)
* help moderating and administrating easier with commands such as /chestsearch (searching for unwanted items in chests)
* made logging easier with /log (shows who tried to swear/post hyperlinks, shows interesting logs such as grief reports and console errors)
* help developers! Using the /debug commands, you can check for all kinds of system information, as well as the current TPS. 
* clean ram! Using /debug clean, you can speed up your server by cleaning the ram
* use advanced NMS coding to do unseen actions (such as instant respawning, picking up skeleton arrows, getting player UUIDS and more!)
Of course, the list goes on, these are just a few of the many features that AllAssets contains.

I'm a plugin developer, why should I be interested?
---------------------------------------------------
Well then, you're in luck. You can check out the [Developer documentation](https://github.com/Skepter/AllAssets/blob/master/Developer%20documentation.md) to get started.
