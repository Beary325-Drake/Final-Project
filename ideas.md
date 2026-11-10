I think a text based CYOA would be a cool idea to start with. We could implement a combat system, inventory & equipment, and a travelling system.

We'd at minimum want a Character Superclass, containing Player and Enemy.
Those would have stats like HP, def, atk, and maybe magic ability. Character would have inventory and potions, monster would have some extra stats, maybe scaling or special perks.


From there we'd probably want to implement a locations superclass, so we can outline what locations are what, and
perhaps some special traits they have. Locations could have negative effects on certain gear due to climate, weather, or otherwise.

Other classes we may need:

    Inventory management class that stores what items the player can use.

    Item class that can be stored in the inventory.

    Combat manager class that handles turns and attack order.

    Skill tree class if we decide to include leveling up and improving skills.

I think that for the progression, we could split the CYOA into 3 zones, each one being its own location that you trudge through. I'm pulling this idea from "An Average Campaign" on roblox, which utilizes a very similar system to
progress the players by splitting their own CYOA into zones.
Perhaps zone 1 would be like a forest, then the player could go to a river or mountain, and finally the volcano to fight the final boss which could be a dragon or evil villain or something.

New Ideas:
Keep it singleplayer, so we don't need to implement multiple characters.
Instead of Multiple players, we could add another class for "Companions" who can maybe extend enemy?

Every character should have a special ability that costs mana:
- Players will get it through the skill tree (AoE, High single target, status effects?)
- Enemies will have it by default (Probably just bonus damage, as a punishment for not killing the enemy in a certain amount of turns.)
- Companions will be dependant on what companion//ally the player has.