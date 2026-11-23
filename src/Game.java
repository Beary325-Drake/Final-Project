import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

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
        input.close();

    }
    /**
     * starts up the game with a new player, currently puts you straight into combat
     */
    private static void play () {
        System.out.println("What is the name of your hero: ");
        String characterName = input.next().trim();
        Object[] playerStats = PlayerApi.getPlayerById(1);
        player = new Player(
                (int) playerStats[0],
                (int) playerStats[1],
                (int) playerStats[2],
                (int) playerStats[3],
                (String) playerStats[4]
            );
        //player = new Player(100, 10, 5, 1, characterName);
        player.setCurrentLocation(createLocations());
        player.setSkillTree(createPlayerSkillTree());
        // the root skill starts unlocked, so add it to the list the player picks from in combat
        player.getUnlockedSkills().add(player.getSkillTree().getRoot().getSkill());
        while (true)
            {
                player.setMana(player.getMaxMana());
                for (int i = 0; i < 1; i++)
                {
                    combat();
                }
                if (player.getCurrentLocation().getLocations().size() == 0)
                {
                    System.out.println("You have reached all locations, good job " + player.getName());
                    break;
                }    
                player.progressLocation(input);
            }
            
    }



    /**
     * creates several test locations to make sure they work
     * TODO: replace with a function that gets the locations from the database
     */
    private static Location createLocations()
    {
        Location forest = new Location("Forest", "A forest with weak enemies and trees");
        Location cave = new Location("Cave", "A cave, filled with rocks and stones");
        Location mountain = new Location("Mountain", "A frigid mountain peak");
        
        Location deepCave = new Location("Deep Cave", "A deep cave, near the bottom of the earth");
        Location glacier = new Location("Glacier", "A impossibly tall cliff face of ice");
        
        cave.addLocation(deepCave);
        mountain.addLocation(glacier);

        forest.addLocation(cave);
        forest.addLocation(mountain);

        return forest;
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
                Random rand = new Random();
                System.out.println("You attempt to flee...");
                if (rand.nextInt(10) > 6)
                {
                    enemyList.removeAll(enemyList);
                    System.out.println("And succeed!"); 
                }
                else
                {
                    System.out.println("And fail!");
                    enemyTurn();
                    break;
                }
                break;
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
        player.setMana(player.getMana() + 1);
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
