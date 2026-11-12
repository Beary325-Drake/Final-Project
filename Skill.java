import java.util.Random;

public class Skill 
{
    private String name;
    private int power;
    private boolean unlocked;
    /**
     * the mana cost of using the skill
     */
    private int cost;
    /**
     * how many turns the skill is currently on cooldown for
     */
    private int cooldown;
    /**
     * the number of turns that the skill gets put on cooldown for when used
     */
    private int maxCooldown;

    /**
     * add more types later on, and keep track of what they do here
     * example of assigning a skill with an enum:
     * skill.setSkillType(SkillType.HEAL);
     * 
     * ATTACK: deals damage to a chosen target
     * HEAL: heals self
     * MAXHEALTH: increases max health
     * STRENGTH: provides a temp damage buff
     */
    public enum SkillType
    {
        ATTACK, HEAL, MAXHEALTH, STRENGTH
    }

    private SkillType type;


    public Skill(String name, SkillType type, int cost, int power, int cooldown, int maxCooldown)
    {
        this.setName(name);
        this.setSkillType(SkillType.ATTACK);
        this.setCost(cost);
        this.setPower(power);
        this.setCooldown(cooldown);
        this.setMaxCooldown(maxCooldown);
        this.unlocked = false;
    }

    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
    }

    public boolean getUnlocked()
    {
        return this.unlocked;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public int getCost()
    {
        return this.cost;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setCooldown(int cool)
    {
        this.cooldown = cool;
    }

    public int getCooldown()
    {
        return this.cooldown;
    }

    public void setMaxCooldown(int cool)
    {
        this.maxCooldown = cool;
    }

    public int getMaxCooldown()
    {
        return this.maxCooldown;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public int getPower()
    {
        return this.power;
    }

    public void setSkillType(SkillType type)
    {
        this.type = type;
    }

    public SkillType getSkillType()
    {
        return this.type;
    }

    /**
     * uses a switch case and the type to do stuff
     */
    public void useSkill(Character user)
    {
        switch (type)
        {
            case SkillType.ATTACK:
            {
                /*
                TODO: needs enemy id's for this bit, currently picking random number for choice, maybe replace with player choice
                */
                Random rand = new Random();
                System.out.println("You attack enemy #" + rand.nextInt(200) + " for " + power + " damage!");
            }

            case SkillType.HEAL:
            {
                user.setHealth(user.getHealth() + power);
                System.out.println("You heal for " + power + " health!");
            }

            case SkillType.MAXHEALTH:
            {
                user.setMaxHealth(user.getMaxHealth() + power);
                System.out.println("You gain " + power + " more maximum health!");
            }

            case SkillType.STRENGTH:
            {
                user.setDamage(user.getDamage() + power);
                System.out.println("You feel empowered with " + power + " extra damage!");
            }
        }
    }

}
