import java.util.Random;
public class Creature extends Character {
    private static int nextCreatureID = 1;
    private final int id;

    public Creature(String name, int hp, int atk, int armor)
    {

        // Constructor for all creatures, fixed mana to coincide with the main system.
        // All other constructors are variables, so that each creature can have different stats.
        // identifiers
        this.id = nextCreatureID++; // creates a numerical identifier for the creature
        this.setName(name); // Should be the "type" of creature instead of a unique name. Goblin, Dog, Boss
        // stat block
        this.setMaxHealth(hp);
        this.setHealth(hp);
        this.setMaxMana(6);
        this.setMana(0);
        this.setDamage(atk);

        this.setArmor(armor);
        this.setAlive(true);
        this.setDefending(false);
        addBasicAttack();
    }
    
    // Resets creature IDs at the start of each round
    // TODO: (Later Implementation) Make companions function differently between combats, should be different from monsters and bosses.
    // (Maybe they get their own system for it?)
    public static void resetIds() 
    {
        nextCreatureID = 1;
    }

    public int getId() 
    {
        return id;
    }
    
    /**
     * currently chooses a random skill to use, perhaps add logic so enemies act smart
     */
    public void useSkill(Character target)
    {
        Random rand = new Random();
        int choice = rand.nextInt(getUnlockedSkills().size());
        Skill chosenSkill = getUnlockedSkills().get(choice);
        useSkill(chosenSkill, target);
    }


}


// Note: Try to fit all the stuff for enemies and companions in here.
//       Make a subclass for bosses so that they can use special skills.
