# Nametag Maker
A plugin for Spigot and Paper Minecraft servers which allows the creation and customization of nametags. 
## Permissions
- __nametagmaker.admin__ - _The admin permission for Nametag Maker. Allows access to commands and in game nametag editors._ <br />
- __nametagmaker.update__ - _Anytime a player joins with this permission a check is made to see if the plugin is up to date. If it's not they get annoyed with a message._ <br />
## Commands
- __/nametag__ - Displays plugin status and version information
- __/nametag disable__ - Disables the plugin during runtime in case of emergencies or just desire to disable it.
- __/nametag enable__ - Enables the plugin during runtime if for some reason it was disabled.
- __/nametag help__ - Sends a list of all the commands for the plugin.
- __/nametag refresh__ - Refreshes the nametags of all players currently logged into the game.
- __/nametag reload__ - Reloads the configuration, language, and tags files after which it also refreshes the nametags of all players.
## Configuration
- Config. In the config currently you will only find an option to disable the plugin. This will disable all functionality with the exception of the __/nametag__ command along with its subcommands. <br />
- Lang. In `lang.yml` you can modify just about any messages sent to players and the console.  <br />
- Tags. This is likely what you're here for. In `tags.yml` you can declare all of your Nametag Groups along with their prefixes, colors, suffixes, permission nodes, and even specific players that are assigned. Let me know if more information needs to be added to the header of `tags.yml` but I feel it does a good job describing each tag. 
## How it Works
Nametag Maker uses vanilia Minecraft's teams which are an extension of the scoreboards system to change the display color of users and to apply prefixes and suffixes to names. Using teams also has the unintended but beneficial side effect of changing the tablist colors along with displaying prefixes and suffixes.
## Version-ing
This project uses the following versioning scheme.
- __0.0.+1__ - _This indicates a small change or bug fix._ 
- __0.+1.1__ - _An increment of the middle number means new features are available._
- __+1.1.1__ - _A new major feature is availible which may or not be backwards compatible. Make backups and be prepared to reconfigure._
