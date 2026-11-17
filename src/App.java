public class App {
    public static void main(String[] args) throws Exception {
        GameDatabase.initialize();
        new Game().run();
    }
}
