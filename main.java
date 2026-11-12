import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    
    /* Menu section: Kinda barebones to start, but is what starts when you run main.java
       Will eventually have more things to do, like change the difficulty, or maybe
       a monster encyclopedia to encourage the player to encounter all monsters and equipment.
    */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.println("=== Main Menu === ");
                System.out.println("1. Play");
                System.out.println("2. Quit");
                System.out.println("Please select an option: ");

                String menuSelection = input.nextLine().trim();

                switch (menuSelection) {
                    case "1":
                        play(input);
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
    }
    private static void play (Scanner input) {
        System.out.println("What is the name of your hero: ");
        String characterName = input.nextLine();
        Player player = new Player(100, 10, 0, 1, characterName);
        Creature enemy = new Creature("Goblin", 30, 6, 0);
        System.out.println("A(n) " + enemy.getName() + " appears!!");
        // Debug:
        System.out.println("Enemy ID: " + enemy.getId());

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println(player.getName() + " HP: " + player.getHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            // Note: Just a placeholder for now, I want enemy health to be hidden to the player
            //       so that each round it just gives a ballpark:
            //       Goblin looks healthy, Goblin is wounded, Goblin is on brink of death, etc.
            System.out.println("-= Select an action. =-");
            System.out.println("1. Attack");
            System.out.println("2. Flee");
            String choice = input.nextLine().trim();
            if (choice.equals("2")) {
                System.out.println("You attempt to flee...");
                System.out.println(" and succeed!!");
                return;
            }

            if (!choice.equals("1")) {
                System.out.println("Invalid choice. Please select: '1' or '2' ");
                continue;
            }
            // Turn Order: Player damages enemy, on fatal blow, combat breaks.
            int damage = Math.max(0, player.getDamage() - enemy.getArmor()); 
            enemy.setHealth(enemy.getHealth() - damage);
            if (!enemy.isAlive()) {
                System.out.println("The " + enemy.getName() + " is slain!");
                break;
            }
            // Turn Order: Enemy damages player. On fatal blow the player dies, and combat breaks.
            damage = Math.max(0, enemy.getDamage() - player.getArmor());
            player.setHealth(player.getHealth() - damage);
            System.out.print("The " + enemy.getName() + "swings at you, dealing " + damage + " damage.");
            if (!player.isAlive()) {
                System.out.println("You are slain in battle. . .");
            } else {
                System.out.println("Combat is over.");
            }
        }

    }
}
