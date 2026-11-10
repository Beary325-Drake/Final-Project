public class Player extends Character
{
    /**
     * TODO: replace with a location class once they are implemented
     */
    private int currentLocation;
    
    /**
     * TODO: replace with a skill tree class once they are implemented
     */
    private int skillTree;

    private String name;
    /**
     * Default constructor for the Player class
     */
    public Player(int hp, int atk, int mana, int armor)
    {
        this.setHealth(100);
        this.setMaxHealth(100);
        this.setDamage(10);
        this.setMana(0); // mana is gained in fight, and should always start at 0
        this.setMaxMana(6); 
        this.setArmor(0);
        this.setAlive(true);
        this.setDefending(false);
        this.setName("Bob");
    }

    /**
     * Constructor for the Player class, with parameters for each attribute
     */
    public Player(int health, int damage, int mana, int armor, String name)
    {
        this.setHealth(health);
        this.setMaxHealth(health);
        this.setDamage(damage);
        this.setMana(mana);
        this.setMaxMana(mana);
        this.setArmor(armor);
        this.setAlive(true);
        this.setDefending(false);
        this.setName(name);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public void die()
    {
        this.setAlive(false);
        System.out.println("You died");
    }

     
}
