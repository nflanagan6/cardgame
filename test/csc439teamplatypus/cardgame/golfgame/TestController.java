package csc439teamplatypus.cardgame.golfgame;
import com.google.common.truth.Truth;
import csc439teamplatypus.cardgame.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class TestController {

    Controller testingController;
    Random testingRandom;

    @BeforeEach
    public void setUp() {

        testingController = new Controller(new CLIView());
        testingRandom = new Random(System.currentTimeMillis());
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

    @Test
    public void drawDiscard_swapsCardToDiscard_and_topOfDiscardPile() {

        testingController.setNumberOfPlayers(2);

        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo(0);

        for (int i = 0; i < 6; i ++) {
            testingController.getPlayerHand(0)[i] = testingController.drawFromPile();
        }

        testingController.discard(testingController.drawFromPile());

        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo(1);

        Card originalDiscard = testingController.viewTopOfDiscardPile();

        int indexOfCardToSwap = testingRandom.nextInt(6);
        Card cardToSwap = testingController.getPlayerHand(0)[indexOfCardToSwap];

        testingController.drawDiscard(0, indexOfCardToSwap);

        Truth.assertThat(cardToSwap).isEqualTo(testingController.viewTopOfDiscardPile());
        Truth.assertThat(testingController.getPlayerHand(0)[indexOfCardToSwap]).isEqualTo(originalDiscard);
        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo( 2);
    }




}