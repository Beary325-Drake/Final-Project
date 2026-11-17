package src;
public class App {
    public static void main(String[] args) throws Exception {
        CreatureDatabase.initialize();
        new Game().run();
    }
}
