public class Player extends Character
{
    /**
     * TODO: replace with a location class once they are implemented
     */
    private int currentLocation;

    private int skillPoints;

    private String name;
    /**
     * Default constructor for the Player class
     */
    public Player(int hp, int atk, int mana, int armor)
    {
        this.setMaxHealth(100);
        this.setHealth(100);
        this.setDamage(10);
        this.setMaxMana(6);
        this.setMana(0); // mana is gained in fight, and should always start at 0
        this.setArmor(0);
        this.setAlive(true);
        this.setDefending(false);
        this.setName("Bob");
        this.setSkillPoints(0);
    }

    /**
     * Constructor for the Player class, with parameters for each attribute
     */
    public Player(int health, int damage, int mana, int armor, String name)
    {
        this.setMaxHealth(health);
        this.setHealth(health);
        this.setDamage(damage);
        this.setMaxMana(mana);
        this.setMana(mana);
        this.setArmor(armor);
        this.setAlive(true);
        this.setDefending(false);
        this.setName(name);
        this.setSkillPoints(0);
    }

    public void setSkillPoints(int points)
    {
        this.skillPoints = points;
    }

    public int getSkillPoints()
    {
        return this.skillPoints;
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
        if (isAlive())
        {
            System.out.println("You died");
        }
        this.setAlive(false);
    }

    public void unlockSkill(Skill skill)
    {
        if (!getSkillTree().hasSkill(skill))
        {
            SkillNode node = getSkillTree().findNode(skill);
            if (node.canLearn())
            {
                if (!node.getUnlocked())
                {
                    skillPoints -= 1;
                    node.setUnlocked(true);
                    getUnlockedSkills().add(skill);
                }
            }
        }
    }

    public void printSkillOptions()
    {
        int count = 1;
        for (Skill skill : getUnlockedSkills())
        {
            count += 1;
            System.out.println(count + ".   " + skill.getName());;
        }
    }

     
}
