# Farmlands
A world of farmlands where one becomes a farmer and survives on making farms, harvesting crops and managing animals.
Consisting out of the following changelogs...

**SMALL UPDATES TO PREVIOUS CHANGED**
- Added view farms to the farm menu to instantly go to the overview of all of your farms.
- Added a left and right click option to all of the farms you own for quick teleport actions.

![image](https://user-images.githubusercontent.com/49062377/185320588-025bedb0-667c-4bb1-aa7e-1405d2a36afc.png)


Changes 2022/07/20 22:47 CEST
**BUG FIXES**
- Fixed deleting all farms causes you to fail at buying a new farm.
- Fixed selecting and renaming the wrong farms when on another farm.
- Fixed getting an error when going back 1 menu and then move forward again.
- Fixed naming your new farm something other than your other farms.
- Fixed other internal command issues.

**ADDED FEATURES**
- Updated command `/farms`
*You can now buy new farms in this menu.*
- You can now add up to 5 farms, naming and editing them according to your likings.
- You can now move between your farms.
- You can now view a overview of all your farms.
- You can now edit, rename, adjust, delete and shop for each individual farm, from any location/world/farm.
- Massive colorcode overhaul of all menus.

![image](https://user-images.githubusercontent.com/49062377/185320425-623babce-b01d-4baa-b918-687538f8addc.png)


Changes 2022/07/20 17:00 CEST
**BUG FIXES**
- Fixed broken shops and other GUI menus that were depended on being on your own farm. *You can now use these anywhere*
- Fixed custom configuration system.

**ADDED FEATURES**
- Added implementations for multiple farms, to move between your farms, to view your owned farms and to use each shop/rules/data per farm.
- Added custom item creation for the economy/shop, to sell or buy items.
*Items are no longer hard coded but can now be added to the shop using a simple format in the plugin files.*
*Any item can be added to the shop with any custom setting. More options will come and go when needed.*

![image](https://user-images.githubusercontent.com/49062377/185320320-8335bcd7-e4e2-42a2-848f-9c96e03e2e49.png)


Changes 2022/07/08 11:38 CEST
**BUG FIXES**
- Prevented from having a negative balance.
- Added a catch incase you have a negative balance.
- Removed infinite hoe'ing bug for creative mode and survival.
- Fixed alot of other issues.


**ADDED FEATURES**
- You can now access Shop, Farm menu and more while outside your farm.
- Added the `/farms` command.
- Added compalibility to have more than 1 farm (unlimited farms).
- The `/shop` and `/farm menu/rules` command will apply to the farm you currently are on or the farm you were on last (if you own it).
- This also applies to `/farm home`.

**KNOWN ISSUES**
- `/farm home` brings you into the ground half way through.



Changes 2022/07/06 15:41 CEST
**MASSIVE #roadmap UPDATE**
- Finnished most main roadmap categories.

**ADDED FEATURES**
- Added new command `/farm setwarp` 
- Added new command `/farm warp <player>` *player will soon be replaced with the farm name*
- Added warps to every farm. You can set a single warp to each of your farms, allowing other players to visit your farm at any given moment.
- Added Farm Rules to the `/farm menu` GUI. 
- Added new GUI for Farm Rules. 
- Added new Rules for Farm Rules. 
                  - Disable or Enable Mob Spawning for each farm. *By default on disabled*
                  - Publish or Privatize each farm for others. *Published farms can be warped to by others, disabled on default*
- Improved database to store locations and more such as custom `sethome` and `setwarp`.
- Implemented several database error catchers to prevent losing data on invalid reading attempts.
- Added new command `/give <player> <amount>`.
- You can now give other players money.
- Breaking farmland by hand, water or other actions now loses the farmland for ever and you won't get anything in return. *Be gentle with your farms*
- Polished methods on detecting using hoe's on grass and dirt.
- Added list of all public farms after typing `/farm warp`.
- Removed farm value from Farm Stats GUI.

**KNOWN ISSUES**
- `/farm home` brings you into the ground half way through. *Not sure if this still occurs.*
- `/farm warp <player>` still has to be modified to display farmnames.

![image](https://user-images.githubusercontent.com/49062377/185320114-79786773-9945-494c-b008-92153b977787.png)



Changes 2022/07/06 03:15 CEST
**BUG FIXES**
- `/farmland` command changed to `/land`.


**ADDED FEATURES**
- Player and farm data can now be saved seperatly to prevent data loses incase of a server outage/crash. *Implementation on when player/farm data should be saved is yet to be decided*

**KNOWN ISSUES**
- `/farm home` brings you into the ground half way through.
- `/farm sethome` does not save through restarts.
- `/farm sethome` moves the center point of where you can and cant build in your farm.



Changes 2022/07/05 20:40 CEST
**BUG FIXES**
- Improved all illegal block interacts.
- Now repeatedly says what to do for new players without a farm when they join.
- Item frame interaction disabled on all illegal interactable worlds.
- You now spawn properly after dying anyonewhere in spawn.
- Falling into void at spawn teleports you back to spawn without dying.
- You now die from falling into the void when falling of your farm into the void rather than teleporting back to your farm.
- By default inventory is kept when dying in your farm *let me know if we should disable inventory keep when dying from a player, mob or any other type of death except void fall.*
- Throwable items are now disabled anywhere except your own farm.

**ADDED FEATURES**
- Improved API with custom events.
- Cleaned up/Improved all interaction events.
- Database overhaul *All players (farmers) and all farms are now stored on seperate directories instead of all in a single YML file.*

**KNOWN ISSUES**
- `/farm home` brings you into the ground half way through.
- `/farm sethome` does not save through restarts.
- `/farm sethome` moves the center point of where you can and cant build in your farm.



Changes 2022/06/29 21:06 CEST
**BUG FIXES**
- Wheat is now replaced with seeds in the buy shop. *if you dont have a farm yet, this will bring you to the farm shop*

**ADDED FEATURES**
- New command alias `/fl` for the same results as `/farmland`.
- New command `/shop` for the same results as `/farm shop`.
- Added `/farm sethome` to set your home. *Note this is a temporary feature as currently this spawn will be reset when the server restarts.*

**KNOWN ISSUES**
- `/farm home` brings you into the ground half way through.
- `/farm sethome` does not save through restarts.



Changes 2022/06/27 19:47 CEST
**ADDED FEATURES**
- New command `/farmland` to check how much farmland you own per farm.
- You can now buy and sell farmland in the shop.
- Changed item values to be sold for less than for what you can buy them.
- Added a improved method of finding the sign for your farmname during the first time you generate a farm.
- Added farmborders *you will be able to increase your farm borders with ingame money earned by farming soon*
- Added farmland as a unique item. You can only use your hoe on the farm when you have atleast 1 farmland on your farm.



Changes 2022/06/26 17:04 CEST
**ADDED FEATURES**
- `/balance` is now triggerable with `/bal`
- Added multipliers for each shop item (1x, 32x and 64x) so you can buy or sell in bulk.
- Added a simple shop system so its easy to add and remove any minecraft item, as well as changing the values.
- Added go back to menu, go back to shop options in each menu.



Changes 2022/06/25 02:26 CEST
**BUG FIXES**
- Cleaned up code, faster GUI loading.

**ADDED FEATURES**
- New command `/balance` *Short version '/bal' will be added soon.*
- Improved API for third party plugins.
- Added shop GUI (temporary display)
- Added shop economy to the game. You can now sell and buy items and earn your money. 
- Note: Currently only Wheat is enabled for the shop. Other items will follow soon. 
- Enter the shop by using `/farm shop` or click on 'farm shop' from the farm menu at `/farm` once you bought your first farm.



Changes 2022/06/17 12:02 CEST
**BUG FIXES**
- Cleaned up alot of code for a faster iteration.

**ADDED FEATURES**
- Added custom economy into the farmlands plugin.
- Added API for other plugins to access the farmsplugin so more plugins can be build and connect with the core of the server.



Changes 2022/06/13 03:09 CEST
**BUG FIXES**
- GUI commands will no longer double (or more) execute.
- Renaming improved and is now GUI compatible or by command.



**ADDED FEATURES**
- New GUI commands added.
- Added new ingame messages for each feature, action, warning or informative message.
- Added prefix for all farmlands plugin messages.



Changes 2022/06/11 02:54 CEST
**BUG FIXES**
- Farm sign will now be able to display longer usernames correctly.

**ADDED FEATURES**
- Removed the constant chat warning for breaking blocks at spawn.
- Farmshop GUI is improved and extended. Multiple farm types are added.
- Farm GUI is improved and extended.
- The `/farm` command will now open the Farm GUI for farm options and information.
- You can now rename your farm unlimited times!
- You can now view your updated farmname.
- Farm top has been added (prototype)
- Farm value has been added (prototype)
- Farm statistics has been added (prototype)
- The following commands are added:
`/farm home`
`/farm rename`
`/farm shop`
`/farm menu`
`/farm help`



Changes 2022/06/10 10:30 CEST
**BUG FIXES**
- Players can no longer modify armor stands in spawn or at other player's farms.
- Fixed the /farm issues.
*Comment: Bukkit has a certain way of loading non-default worlds. The issue has been fixed. Farms were reset once more.*

**ADDED FEATURES**
- Created a build environment for updating the spawn or starter farms.
- Added special perms to the build environment.



Changes 2022/06/08 20:24 CEST
**BUG FIXES**
- Not being able to teleport to your farm and instead receiving the *"Your farm is corrupted. Too bad!"* message.
- Falling in the void when your world is deleted now sends you to spawn.
- Falling in the void anywhere will teleport you to the spawn of that world.
- Sign at your first farm will contain your first farm name correctly.

**ADDED FEATURES**
- Implementation to own more than 1 farm.
- Disabled hunger and fall damage in spawn.
- `/farm` will now bring up the menu to buy your first farm. `/farm` again will teleport you to your farm if you already have one.
