import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    
    /* Menu section: Kinda barebones to start, but is what starts when you run main.java
       Will eventually have more things to do, like change the difficulty, or maybe
       a monster encyclopedia to encourage the player to encounter all monsters and equipment.
    */
    private static ArrayList<Creature> enemyList = new ArrayList<Creature>();
    private static Player player;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) 
    {
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
    
    private static void play () {
        System.out.println("What is the name of your hero: ");
        String characterName = input.next().trim();
        player = new Player(100, 10, 0, 1, characterName);
        
        combat();
    }

    private static void combat()
    {
        createEnemies(8);
        while (player.isAlive() && enemyList.size() > 0)
        {
            combatTurn();
        }
    }

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
                /*
                TODO: replace with a choice so you can choose what enemy to hit
                */
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
                // TODO: Add on chance for escape to fail (80-20) & remove escape chance for bosses. (0-100)
                return;
            }
            default:
            {
                System.out.println("Invalid choice. Please select: '1' or '2' ");
                break;
            }

        }
    }

    private static void playerTurn(Creature enemy)
    {
        int damage = Math.max(0, player.getDamage() - enemy.getArmor()); // I want to change this to a range when we implement equipment
        /*
         TODO: add a choice for what skill to use
         */
        System.out.println("You swing!");
        enemy.setHealth(enemy.getHealth() - damage);
        if (!enemy.isAlive()) {
            System.out.println("The " + enemy.getName() + " is slain!");
            enemyList.remove(enemy);
        }
    }

    private static void enemyTurn()
    {
        for (Creature enemy : enemyList)
        {
            int damage = Math.max(0, enemy.getDamage() - player.getArmor()); // I want to change this to a range when we implement equipment
            player.setHealth(player.getHealth() - damage);
            System.out.println("The " + enemy.getName() + " swings at you, dealing " + damage + " damage.");
        }
    }

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

    private static void printCombatants()
    {
        System.out.println(player.getName() + " HP: " + player.getHealth());
        for (Creature enemy : enemyList)
        {
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
        }
    }

    private static void printOptions()
    {
        System.out.println("-= Select an action. =-");
        System.out.println("1. Attack");
        System.out.println("2. Flee");
    }
}
