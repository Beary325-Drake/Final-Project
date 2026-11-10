I think a text based CYOA would be a cool idea to start with. We could implement a combat system, inventory & equipment, and a travelling system.

We'd at minimum want a Character Superclass, containing Player and Creature.
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
Zone 1 will be a forest, and It'll branch off into 3 areas, then those areas will branch again, before converging on the final location.

New Ideas:
Keep it singleplayer, so we don't need to implement multiple characters.
Instead of Multiple players, we could add another class for "Companions" who can maybe extend enemy?
----------------------------------------
Class derivations:
- Character contains: Universal Stats & Mechanics used by every character (Player, Creature, Boss, Companion)
- Player contains: Skill tree, Name, Locator, TBD
- Creature contains: creature id, TBD
- Boss contains: abilities, TBD
- Companion contains: abilities, should be an ally, TBD
----------------------------------------
Skill Obtainment:
- Players will get it through the skill tree (AoE, High single target, status effects?)
- All enemies will have skills. Bosses and Companions might have multiple, whereas enemies will just have 1.

----------------------------------------
IDs:
Should be split into sides:

Allied ID(s):    |    Enemy ID(s):
Player           |    Monster
Companion        |    Boss
----------------------------------------
Locations:
!!IMPORTANT!! 
This section would be very time consuming and painful to implement within our timeframe. Think of it more as a potential end goal rather than a definite TODO.
# Forest (Starting Area[1]) (Dropdown Available)
- Branches off into Desert or Tundra after 8 nodes

- Creatures: Goblin, Faeling, Wolf, 
- Companions: Little Pupper, Fairy, Domestic Rock
- Region Boss: The Tollkeeper
    - Ability 1, Toll (2 Energy): Deals damage to the player once, with a 10% chance of reducing armor by 1.
    - Ability 2, TBD (6 Energy)
# Desert       (Area 2) (Dropdown Available)
- Branches off into Caves or Burning City after 10 nodes

- Creatures: Mummy, 
- Companions:
- Region Boss:
    - Ability 1, TBD, (# Energy):
    - Ability 2, TBD, (# Energy):
# Tundra       (Area 2) (Dropdown Available)
- Branches off into Caves or Burning City after 10 nodes

- Creatures: Snowman, 
- Companions:
- Region Boss:
    - Ability 1, TBD, (# Energy):
    - Ability 2, TBD, (# Energy):
# Caves        (Area 3) (Dropdown Available)
- Branches off into The Forgotten Mountain after 12 nodes

- Creatures: 
- Companions:
- Region Bosses:
    Boss 1, TBD, Node 6:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 2, TBD, Node 12:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
# Burning City (Area 3) (Dropdown Available)
- Branches off into The Forlorn Citadel after 12 nodes

- Creatures: 
- Companions:
- Region Bosses:
    Boss 1, TBD, Node 6:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 2, TBD, Node 12:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
# The Forlorn Citadel (Area 4) (Dropdown Available)
- An ending is reached after 6 nodes

- Creatures: 
- Companions:
- Region Bosses:
    Boss 1, TBD, Node 1:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 2, TBD, Node 2:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 3, TBD, Node 5:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 4, TBD, Node 6:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
# The Forgotten Mountain (Area 4) (Dropdown Available)
- An ending is reached after 6 nodes

- Creatures: 
- Companions:
- Region Bosses:
    Boss 1, TBD, Node 1:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 2, TBD, Node 2:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 3, TBD, Node 5:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
    Boss 4, TBD, Node 6:
        - Ability 1, TBD, (# Energy):
        - Ability 2, TBD, (# Energy):
# ----------------------------------------
Location Progression:
!!IMPORTANT!!
This section follows the same ruling as the previous one. If its too much we can always downscale it to fit what we want for it to be.
# How a location should work
- Locations should have a designated amount of nodes. Each node is a world interaction followed by a combat encounter.
    - Area 1: 8 nodes (1 boss)
    - Area 2: 10 nodes (1 boss)
    - Area 3: 12 nodes (2 bosses)
    - Area 4: 6 nodes (4 bosses)
- Locations should have a special effect on the characters
    - Area 1: None
    - Area 2: Varies
    - Area 3: Varies
    - Area 4: TBD
- 
TODO:
# URGENT
- Implement main function
    - Should have a sort of menu, maybe we could have character slots?
    - Menu should have a welcome statement, explain the premise of the game, and then initialize one if the user requests.
    - Options: Start, Difficulty, Tutorial, Saves (implement later), Credits, Dictionary
        - Dictionary should just have an overview of game content. Skills, Game mechanics, etc.
(main should serve as the launching point of the game, so it will likely end up as an in-progress until the project is done)
- Complete Creature class
    - We'll probably need to discuss how we want some of the stuff to function prior to continuing work on it, as we'll need to discuss how we want to implement unique creatures. (Forest could have goblins, and other areas have their own unique creatures with a special stat block)
- Design Magic & Mana System
    - Right now its super bare bones. Each character gains 1 mana per turn, up to their max mana, and on their turn can spend it how they like. I'd like to implement a cooldown for two reasons: 1. it mixes up how the player can do things, and prevents them from spamming the same thing over and over again. 2. It makes boss fights and creature encounters scripted, so people who have played more have more "Game Knowledge" as a reward for their investment. (Also it prevents monsters from spamming abilities too.)
- Reorganize current classes
    - If taking a break from coding or planning, rearranging the code within the classes so that similar things are grouped can significantly boost the code's readability. (This problem may not always exist.)



# FOR THURSDAY
- Make a barebones demo
    - Character & skill tree
    - Location
    - Monster
    - Main
    - Logic
    1. Finish Creature.java so we can implement creatures
    2. Create Main.java so we can present a functioning & running game