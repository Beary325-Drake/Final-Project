import java.util.ArrayList;
import java.util.Scanner;
public class Player extends Character
{

    private Location currentLocation;
    private int skillPoints;
    private String name;
    private int level;
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
    public Player(int health, int damage, int maxMana, int armor, String name)
    {
        this.setMaxHealth(health);
        this.setHealth(health);
        this.setDamage(damage);
        this.setMaxMana(maxMana);
        this.setMana(maxMana);
        this.setArmor(armor);
        this.setAlive(true);
        this.setDefending(false);
        this.setName(name);
        this.setSkillPoints(0);
    }

    public void setCurrentLocation(Location location)
    {
        this.currentLocation = location;
    }

    public Location getCurrentLocation()
    {
        return this.currentLocation;
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

    /**
     * unlocks a certain skill
     */
    public void unlockSkill(Skill skill)
    {
        if (getSkillTree().hasSkill(skill))
        {
            SkillNode node = getSkillTree().findNode(skill);
            if (node.canLearn())
            {
                if (!node.getUnlocked())
                {
                    if (skillPoints >= 1)
                    {
                        skillPoints -= 1;
                        node.setUnlocked(true);
                        getUnlockedSkills().add(skill);
                        System.out.println("You have unlocked " + skill.getName() + ", and now have " + skillPoints + " skill points");
                    }
                    else
                    {
                        System.out.println("Too few skill points");
                    }
                }
                else
                {
                    System.out.println("Skill is already unlocked");
                }
            }
            else
            {
                System.out.println("cannot learn that skill yet");
            }
        }
    }

    public void printSkillOptions()
    {
        System.out.println("Select one of the following skills: ");
        int count = 1;
        for (Skill skill : getUnlockedSkills())
        {
            System.out.println(count + ".   " + skill.getName() + "     Cost: " + skill.getCost() + "   Cooldown: " + skill.getCooldown());;
            count += 1;
        }
    }

    /**
     * prints out all skills that can be unlocked, takes and input, and unlocks skill based on input
     */
    public void levelUp(Scanner keyboard)
    {
        level += 1;
        System.out.println(getName() + " has reached level " + level + "!");
        ArrayList<SkillNode> canUnlock = new ArrayList<SkillNode>();

        for (Skill skill : getUnlockedSkills())
        {
            if (getSkillTree().hasSkill(skill))
            {
                SkillNode node = getSkillTree().findNode(skill);
                if (node.getUnlocked())
                {
                    for (SkillNode childnode : node.getChildren())
                    {
                        if (!childnode.getUnlocked())
                        {
                            canUnlock.add(childnode);
                        }
                    }
                }
            }
        }

        if (canUnlock.size() > 0)
        {
            while (true)
            {
                System.out.println("Select one of the following skills to unlock:");


                int counter = 1;
                for (SkillNode node : canUnlock)
                {
                    System.out.println(counter + ".  " + node.getSkill().getName());
                    counter += 1;
                }

                int choice = keyboard.nextInt();
                keyboard.nextLine();

                if (choice > 0 && choice <= canUnlock.size())
                {
                    Skill chosenSkill = canUnlock.get(choice - 1).getSkill();
                    unlockSkill(chosenSkill);
                    System.out.println(chosenSkill.getName() + " is now unlocked!");
                    break;
                }
                System.out.println("Input out of bounds");
            }
        }

        else
        {
            System.out.println("No skills left to unlock!");
        }
    }


    /**
     * prints out location choices and accepts input so the player can choos what location to move to next
     */
    public void progressLocation(Scanner keyboard)
    {
        System.out.println("Choose what location to move to:");
        if (currentLocation.getLocations().size() > 0)
        {
            int counter = 1;
            for (Location location : currentLocation.getLocations())
            {
                System.out.println(counter + ". " + location.getName());
                counter += 1;
            }
        }
        else
        {
            System.out.println("No more locations, you have completed the game!");
        }

        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if (choice > 0 && choice <= currentLocation.getLocations().size())
        {
            setCurrentLocation(currentLocation.getLocations().get(choice - 1));
            System.out.println("You have moved to " + currentLocation.getName() + "!");
            System.out.println(currentLocation.getDescription());
        }
        else
        {
            System.out.println("Index out of bounds");
        }

    }

}