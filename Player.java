public class Player extends Character
{
    /**
     * TODO: replace with a location class once they are implemented
     */
    private int currentLocation;
    
    /**
     * Default constructor for the Player class
     */
    public Player()
    {
        this.setHealth(100);
        this.setDamage(10);
        this.setMana(100);
        this.setArmor(0);
        this.setAlive(true);
        this.setDefending(false);
    }

    /**
     * Constructor for the Player class, with parameters for each attribute
     */
    public Player(int health, int damage, int mana, int armor)
    {
        this.setHealth(health);
        this.setDamage(damage);
        this.setMana(mana);
        this.setArmor(armor);
        this.setAlive(true);
        this.setDefending(false);
    }

}
