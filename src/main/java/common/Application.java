package common;

public class Application {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();

        Game game = new Game();
        game.run();
        System.out.println("application has been closed!");
    }

}
