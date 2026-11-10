public class Creature extends Character {
/**
 * TODO: Implement an ID system so that many creatures can exist at once, while also being organized
 */
    private static int nextCreatureID = 1;
    private final int id;
    private final String name;

    public Creature(String name, int hp, int atk, int armor)
    {

        // Constructor for all creatures, fixed mana to coincide with the main system.
        // All other constructors are variables, so that each creature can have different stats.
        // identifiers
        this.id = nextCreatureID++; // creates a numerical identifier for the creature
        this.name = name; // Should be the "type" of creature instead of a unique name. Goblin, Dog, Boss
        // stat block
        this.setMaxHealth(hp);
        this.setHealth(hp);
        this.setMaxMana(6);
        this.setMana(0);
        this.setDamage(atk);

        this.setArmor(armor);
        this.setAlive(true);
        this.setDefending(false);
    }
    
    // Resets creature IDs at the start of each round
    // TODO: (Later Implementation) Make it so that companion IDs do not reset alongside monsters & bosses.
    // (Maybe they get their own system for it?)
    public static void resetIds() {
        nextCreatureID = 1;
    }

    public int getId() {
        return id;
    }
    public String getName() { // Name exists to pull the type of monster. Ex: Goblin would be a name.
        return name;
    }
        
}
// TODO: Implement an overridden die function which states the creature that died, & maybe it's ID?

// TODO: Implement creature logic

// Note: Try to fit all the stuff for enemies and companions in here.
//       Make a subclass for bosses so that they can use special skills.
