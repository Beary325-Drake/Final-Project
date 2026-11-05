public abstract class character 
/*
Baseline Superclass for all characters in the game.
It should contain basic attributes like:
Health (Which should be an integer that cannot go below 0, and if it reaches 0, the character is dead)
Mana (Should be an integer beteween 0 and 6, and each player should gain 1 per turn unless stated otherwise.)
Armor (Should be an integer that cannot go below 0, TBD)
Damage (Should be an integer that cannot go below 0. It should be used to inflict damage on other characters, or possibly objects.)
 */
{
    private int health;
    private int mana;
    private int armor;
    private int damage;
    private boolean isAlive;
    private boolean isDefending;

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getMana()
    {
        return mana;
    }

    public void setMana(int mana)
    {
        this.mana = mana;
    }

    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor = armor;
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
