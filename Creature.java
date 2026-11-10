public class Creature extends Character {
// 
/**
 * TODO: Implement an ID system so that many creatures can exist at once, while also being organized
 */

    public Creature(int hp, int atk, int armor)
    {
        // Constructor for all creatures, fixed mana to coincide with the main system.
        // All other constructors are variables, so that each creature can have different stats.
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
}
// TODO: Implement an overridden die function which states the creature that died, & maybe it's ID?

// TODO: Implement creature logic

// Note: Try to fit all the stuff for enemies and companions in here.
//       Make a subclass for bosses so that they can use special skills.
