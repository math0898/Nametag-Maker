# Nametag Maker
A plugin for Spigot and Paper Minecraft servers which allows the creation and customization of nametags. 
## Version-ing
This project uses the following versioning scheme.
- __0.0.+1__ - _This indicates a small change or bug fix._ 
- __0.+1.1__ - _An increment of the middle number means new features are available._
- __+1.1.1__ - _A new major feature is availible which may or not be backwards compatible. Make backups and be prepared to reconfigure._
## Permissions
- __nametag.admin__ - _The admin permission for Nametag Maker. Allows access to commands and in game nametag editors._ <br />
## Commands
- __/nametag__ - Displays plugin status and version information
- __/nametag disable__ - Disables the plugin during runtime in case of emergencies or just desire to disable it.
- __/nametag enable__ - Enables the plugin during runtime if for some reason it was disabled.
- __/nametag help__ - Sends a list of all the commands for the plugin.
- __/nametag refresh__ - Refreshes the nametags of all players currently logged into the game.
- __/nametag reload__ - Reloads the configuration, language, and tags files after which it also refreshes the nametags of all players.
## How it Works
Nametag Maker uses vanilia Minecraft's teams which are an extension of the scoreboards system to change the display color of users and to apply prefixes and suffixes to names. Using teams also has the unintended but beneficial side effect of changing the tablist colors along with displaying prefixes and suffixes.
