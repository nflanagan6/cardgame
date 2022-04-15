package csc439teamplatypus.cardgame.golfgame;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCLIView {
    CLIViewTest view = new CLIViewTest();
    Controller controller = new Controller(view);

    @BeforeEach
    public void setUp() {
        CLIViewTest view = new CLIViewTest();
        Controller controller = new Controller(view);
    }

    @Test
    void setPlayer2_DrawFromDeck_TakeCard_ReplaceCard1() {
        view.startGame(2);
        view.nextTurn("Deck", "Yes", 1);
        Truth.assertThat(controller.getDeck().size()).isEqualTo(38);
    }

    @Test
    void setPlayer2_DrawFromDeck_DiscardCard_Flip1() {
        view.startGame(2);
        view.nextTurn("Deck", "No", 1);
        Truth.assertThat(controller.getDeck().size()).isEqualTo(38);
    }

    @Test
    void setPlayer2_DrawFromDiscard_ReplaceCard1() {
        view.startGame(2);
        view.nextTurn("Discard", "Yes", 1);
        Truth.assertThat(controller.getDeck().size()).isEqualTo(38);
    }

    @Test
    void setPlayer6_Quit() {
        view.startGame(6);
        view.nextTurn("QUIT", "Yes", 1);
        Truth.assertThat(controller.getDeck().size()).isEqualTo(67);
    }

    @Test
    void setPlayer2_DrawFromDeckUntilEmpty() {
        view.startGame(2);
        for (int i = 0; i < 40; i++) {
            view.nextTurn("Deck", "Yes", 1);
        }
        Truth.assertThat(controller.getDeck().size()).isEqualTo(0);
    }
}
