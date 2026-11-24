/**
 * an effect that lasts a certain number of turns
 */
public class Effect 
{
    /**
     * how many turns left before this effect runs out
     */
    private int time;
    /**
     * how strong the effect is
     */
    private int strength;

    private Skill.SkillType type;

    public Effect(int time, int strength, Skill.SkillType type)
    {
        setTime(time);
        setStrength(strength);
        setSkillType(type);
    }

    /**
     * applies the effect to a given target, called when once the effect is first applied, used for things like temp stat boosts
     */
    public void applyEffect(Character target)
    {
        switch (type)
        {
            case Skill.SkillType.ATTACK:
            {
                break;
            }

            case Skill.SkillType.HEAL:
            {
                break;
            }

            case Skill.SkillType.MAXHEALTH:
            {
                target.setMaxHealth(target.getMaxHealth() + strength);
                break;
            }

            case Skill.SkillType.STRENGTH:
            {
                target.setDamage(target.getDamage() + strength);
                break;
            }
        }
    }

    /**
     * applies the effect to a given target, called when once every turn, used for things like damage over time
     */
    public void effectTick(Character target)
    {
        switch (type)
        {
            case Skill.SkillType.ATTACK:
            {
                target.setHealth(target.getHealth() - strength);
                break;
            }

            case Skill.SkillType.HEAL:
            {
                target.setHealth(target.getHealth() + strength);
                break;
            }

            case Skill.SkillType.MAXHEALTH:
            {
                break;
            }

            case Skill.SkillType.STRENGTH:
            {
                break;
            }
        }
    }

    /**
     * reverses the effect to a target character, called when the effects is removed
     */
    public void removeEffect(Character target)
    {
        switch (type)
        {
            case Skill.SkillType.ATTACK:
            {
                break;
            }

            case Skill.SkillType.HEAL:
            {
                break;
            }

            case Skill.SkillType.MAXHEALTH:
            {
                target.setMaxHealth(target.getMaxHealth() - strength);
                break;
            }

            case Skill.SkillType.STRENGTH:
            {
                target.setDamage(target.getDamage() - strength);
                break;
            }
        }
    }

    public Skill.SkillType getSkillType()
    {
        return this.type;
    }

    public void setSkillType(Skill.SkillType type)
    {
        this.type = type;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }
}
