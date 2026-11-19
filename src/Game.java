import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    
    /* Menu section: Kinda barebones to start, but is what starts when you run main.java
       Will eventually have more things to do, like change the difficulty, or maybe
       a monster encyclopedia to encourage the player to encounter all monsters and equipment.
    */
    private static ArrayList<Creature> enemyList = new ArrayList<Creature>();
    private static Player player;
    private static Scanner input = new Scanner(System.in);

    public void run() 
    {
        // Game functions:
        boolean running = true;
        while (running) 
        {
            System.out.println("=== Main Menu === ");
            System.out.println("1. Play");
            System.out.println("2. Quit");
            System.out.println("Please select an option: ");
            String menuSelection = input.next();
            switch (menuSelection) 
            {
                case "1":
                    play();
                    break;
                case "2":
                    running = false;
                    System.out.println("Thank you for playing!");
                    break;
                default:
                    System.out.println("Please enter a valid integer. Ex: '1', or '2'");
            }
        }
    }
    /**
     * starts up the game with a new player, currently puts you straight into combat
     */
    private static void play () {
        System.out.println("What is the name of your hero: ");
        String characterName = input.next().trim();
        player = new Player(100, 10, 5, 1, characterName);
        player.setSkillTree(createPlayerSkillTree());
        // the root skill starts unlocked, so add it to the list the player picks from in combat
        player.getUnlockedSkills().add(player.getSkillTree().getRoot().getSkill());
        for (int i = 0; i < 4; i++)
        {
            combat();
        }
        input.close();
    }

    /**
     * created with the help of Claude so i can test skills before the database is finished
     * TODO: replace with a getting the information from the database
     * 
     * builds the player's skill tree, must be called after the player is created
     * Basic Attack is the root and starts unlocked, every other skill has to be unlocked with skill points
     *
     * Basic Attack
     * ├── Heavy Strike
     * │   └── Whirlwind
     * ├── Heal
     * │   └── Fortify
     * └── Battle Cry
     */
    private static SkillTree createPlayerSkillTree()
    {
        // Skill(name, type, cost, power, maxCooldown, duration)
        Skill basicAttack = new Skill("Basic Attack", Skill.SkillType.ATTACK,    0, player.getDamage(), 0, 0);
        Skill heavyStrike = new Skill("Heavy Strike", Skill.SkillType.ATTACK,    2, 20, 2, 0);
        Skill whirlwind   = new Skill("Whirlwind",    Skill.SkillType.ATTACK,    4, 35, 3, 0);
        Skill heal        = new Skill("Heal",         Skill.SkillType.HEAL,      2, 20, 2, 0);
        Skill fortify     = new Skill("Fortify",      Skill.SkillType.MAXHEALTH, 3, 15, 4, 0);
        Skill battleCry   = new Skill("Battle Cry",   Skill.SkillType.STRENGTH,  2, 5,  3, 2);

        SkillNode root = new SkillNode(basicAttack);
        root.setUnlocked(true);

        SkillNode heavyStrikeNode = new SkillNode(heavyStrike);
        SkillNode healNode = new SkillNode(heal);
        SkillNode battleCryNode = new SkillNode(battleCry);
        root.addChild(heavyStrikeNode);
        root.addChild(healNode);
        root.addChild(battleCryNode);

        heavyStrikeNode.addChild(new SkillNode(whirlwind));
        healNode.addChild(new SkillNode(fortify));

        return new SkillTree(root);
    }

    /**
     * initiates combat with some number of enemies
     */
    private static void combat()
    {
        System.out.println("Combat has begun!");
        createEnemies(1);
        while (player.isAlive() && enemyList.size() > 0)
        {
            combatTurn();
        }
        System.out.println("Combat is finished!");
        if (player.isAlive())
        {
            System.out.println("You gain 1 skill point!");
            player.setSkillPoints(player.getSkillPoints() + 1);
            player.levelUp(input);
        }           
    }

    /**
     * a single turn in combat
     * starts with player turn, then the enemy turns
     */
    private static void combatTurn()
    {

        // Note: Just a placeholder for now, I want enemy health to be hidden to the player
        //       so that each round it just gives a ballpark:
        //       Goblin looks healthy, Goblin is wounded, Goblin is on brink of death, etc.
        printCombatants();
        printOptions();
        String choice = input.next().trim();
        switch (choice)
        {
            // Turn Order: Player damages enemy, on fatal blow, combat breaks.
            case "1":
            {
                Creature enemy = enemyList.get(0);
                
                playerTurn(enemy);
                enemyTurn();
                System.out.println("Turn is over.");
                if (!player.isAlive())
                {
                    System.out.println("You are slain in battle. . .");
                }
                // Turn Order: Enemy damages player. On fatal blow the player dies, and combat breaks
                break;
            }
            case "2":
            {
                System.out.println("You attempt to flee...");
                System.out.println(" and succeed!!"); 
                return;
            }
            default:
            {
                System.out.println("Invalid choice. Please select: '1' or '2' ");
                break;
            }

        }
    }

    /**
     * a single turn for the player
     * will gives choice of skill usage later
     * takes the current targeted enemy as input
     */
    private static void playerTurn(Creature enemy)
    {
        Skill chosenSkill;
        
        while (true)
        {
            player.printSkillOptions();
            int choice = input.nextInt();
            input.nextLine();
            if (0 < choice && choice <= player.getUnlockedSkills().size())
            {
                chosenSkill = player.getUnlockedSkills().get(choice - 1);
                break;
            }
            else
            {
                System.out.println("Choice out of bounds, choose again");
            }
        }

        player.useSkill(chosenSkill, enemy);

        if (!enemy.isAlive()) {
            System.out.println("The " + enemy.getName() + " is slain!");
            enemyList.remove(enemy);
        }
    }

    /**
     * a turn for every single enemy in the current enemy list
     */
    private static void enemyTurn()
    {
        for (Creature enemy : enemyList)
        {
            enemy.useSkill(player);
        }
    }

    /**
     * adds to the list of enemies
     */
    private static void createEnemies(int count)
    {
        for (int i = 0; i < count; i++)
        {

            /*
            TODO: once we have locations set up with enemy spawn pools, make it choose a random enemy from the pool
            */
            Creature enemy = new Creature("Goblin", 30, 6, 0);
            enemyList.add(enemy);
        }
    }

    /**
     * prints out the stats of all combatants in the current combat
     */
    private static void printCombatants()
    {
        System.out.println(player.getName() + " HP: " + player.getHealth() + "  MANA: " + player.getMana());
        for (Creature enemy : enemyList)
        {
            System.out.println(enemy.getName() + "  HP: " + enemy.getHealth());
        }
    }

    /**
     * prints out options for combat or fleeing
     */
    private static void printOptions()
    {
        System.out.println("-= Select an action. =-");
        System.out.println("1. Continue combat");
        System.out.println("2. Flee");
    }
}
