package csc439teamplatypus.cardgame.golfgame;

public class Main {
    public static void main(String[] args) {
        CLIView view = new CLIView();
        Controller controller = new Controller(view);
        view.startGame();
    }
}
