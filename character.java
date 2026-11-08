/**
Baseline Superclass for all characters in the game.
It should contain basic attributes like:
Health (Which should be an integer that cannot go below 0, and if it reaches 0, the character is dead)
Mana (Should be an integer beteween 0 and 6, and each player should gain 1 per turn unless stated otherwise.)
Armor (Should be an integer that cannot go below 0, TBD)
Damage (Should be an integer that cannot go below 0. It should be used to inflict damage on other characters, or possibly objects.)
*/
public abstract class Character 
{
    /**
     * the current health of the character, dies if goes below 0
     */
    private int health;
    /**
     * health cannot go above this number
     */
    private int maxHealth;
    /**
     * the current mana of the character
     */
    private int mana;
    /**
     * mana cannot go above this number
     */
    private int maxMana;
    /**
     * reduces damage
     */
    private int armor;
    /**
     * the base damage this character deals 
     */
    private int damage;
    private boolean isAlive;
    private boolean isDefending;


    /**
     * used to kill the character, overide with whatever logic is needed for the creature 
     */
    public void die()
    {
        this.setAlive(false);
    }

    /**
     * used for calculating damage, override to include things like resistances, armor, critital damage, etc
     */
    public int attack()
    {
        return this.getDamage();
    }

    public int getHealth()
    {
        return health;
    }

    /**
     * kills the chracter if health goes to 0 or below
     */
    public void setHealth(int health)
    {
        this.health = Math.clamp(health, 0, maxHealth);
        if (this.health <= 0)
        {
            this.die();
        }
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public int getMana()
    {
        return mana;
    }

    public void setMana(int mana)
    {
        this.mana = Math.clamp(mana, 0, maxMana);
    }

    public int getMaxMana()
    {
        return maxMana;
    }

    public void setMaxMana(int mana)
    {
        this.maxMana = mana;
    }


    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor = Math.max(armor, 0);
    }

    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    /**
     * probably shouldnt be called other than through die(), might remove this function 
     */
    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public boolean isDefending()
    {
        return isDefending;
    }

    public void setDefending(boolean defending)
    {
        isDefending = defending;
    }

}
