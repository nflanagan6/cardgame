package csc439teamplatypus.cardgame.golfgame;
import com.google.common.truth.Truth;
import csc439teamplatypus.cardgame.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestController {

    Controller testingController;

    @BeforeEach
    public void setUp() {

        testingController = new Controller(new CLIView());

    }

    @Test
    public void create52CardDeck() {

        testingController.setNumberOfPlayers(4);
        ArrayList<Card> testingDeck = testingController.getDeck();

        Truth.assertThat(testingDeck).hasSize(52);
    }

    @Test
    public void create104CardDeck() {

        testingController.setNumberOfPlayers(5);
        ArrayList<Card> testingDeck = testingController.getDeck();

        Truth.assertThat(testingDeck).hasSize(104);
    }

    @Test
    public void discardTopCardInDeck() {

        testingController.setNumberOfPlayers(3);
        Card topCard = testingController.drawFromPile();
        testingController.discard(topCard);

        Truth.assertThat(topCard).isEqualTo(testingController.viewTopOfDiscardPile());
    }




}
