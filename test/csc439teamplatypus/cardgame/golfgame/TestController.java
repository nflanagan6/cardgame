package csc439teamplatypus.cardgame.golfgame;
import com.google.common.truth.Truth;
import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;
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
        ArrayList<GolfCard> testingDeck = testingController.getDeck();

        Truth.assertThat(testingDeck).hasSize(52);
    }

    @Test
    public void create104CardDeck() {

        testingController.setNumberOfPlayers(5);
        ArrayList<GolfCard> testingDeck = testingController.getDeck();

        Truth.assertThat(testingDeck).hasSize(104);
    }

    @Test
    public void discardTopCardInDeck() {

        testingController.setNumberOfPlayers(3);
        GolfCard topCard = testingController.drawFromDeck();
        testingController.discard(topCard);

        Truth.assertThat(topCard).isEqualTo(testingController.viewTopOfDiscardPile());
        Truth.assertThat(testingController.viewTopOfDiscardPile().getCardFace()).isEqualTo(CardFace.UP);
    }

    @Test
    public void drawDiscard_swapsCardToDiscard_and_topOfDiscardPile() {

        testingController.setNumberOfPlayers(2);

        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo(0);

        for (int i = 0; i < 6; i ++) {
            testingController.getPlayerHand(0)[i] = testingController.drawFromDeck();
        }

        testingController.discard(testingController.drawFromDeck());

        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo(1);

        Card originalDiscard = testingController.viewTopOfDiscardPile();

        int indexOfCardToSwap = testingRandom.nextInt(6);
        Card cardToSwap = testingController.getPlayerHand(0)[indexOfCardToSwap];

        testingController.drawDiscard(0, indexOfCardToSwap);

        Truth.assertThat(cardToSwap).isEqualTo(testingController.viewTopOfDiscardPile());
        Truth.assertThat(testingController.getPlayerHand(0)[indexOfCardToSwap]).isEqualTo(originalDiscard);
        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo( 2);
    }

    @Test
    public void flipCard_flipsCards() {

        testingController.setNumberOfPlayers(2);
        testingController.setPlayerHands();
        int faceDownCard = -1;

        for (int i = 0; i < 6; i++)
            if (testingController.getPlayerHand(0)[i].getCardFace() == CardFace.DOWN) {
                faceDownCard = i;
                break;
            }

        testingController.flipCard(0, faceDownCard);

        Truth.assertThat(testingController.getPlayerHand(0)[faceDownCard].getCardFace()).isEqualTo(CardFace.UP);
    }

    @Test
    public void drawFromDeck_flipsCardsFaceUP() {

        testingController.setNumberOfPlayers(2);

        Card card = testingController.drawFromDeck();

        Truth.assertThat(card.getCardFace()).isEqualTo(CardFace.UP);
    }

    @Test
    void setPlayerHands_3Players() {
        testingController.setNumberOfPlayers(3);

        testingController.setPlayerHands();
        for (int i = 0; i < testingController.getNumberOfPlayers(); i++) {
            Truth.assertThat(testingController.getPlayerHand(i).length).isEqualTo(6);
        }
        Truth.assertThat(testingController.getDeck().size()).isEqualTo(33);
        Truth.assertThat(testingController.viewTopOfDiscardPile()).isNotNull();
        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo(0);
    }

    @Test
    void setPlayerHand_7Players() {
        testingController.setNumberOfPlayers(7);

        testingController.setPlayerHands();
        for (int i = 0; i < testingController.getNumberOfPlayers(); i++) {
            Truth.assertThat(testingController.getPlayerHand(i).length).isEqualTo(6);
        }
        Truth.assertThat(testingController.getDeck().size()).isEqualTo(61);
        Truth.assertThat(testingController.viewTopOfDiscardPile()).isNotNull();
        Truth.assertThat(testingController.getNumberOfPlayedTurns()).isEqualTo(0);
    }

    @Test
    void getPlayerScores_calculatesScoresCorrectly() {

        testingController.setNumberOfPlayers(4);
        testingController.setPlayerHands();

        for (int i = 0; i < testingController.getNumberOfPlayers(); i++) {
            GolfCard[] hand = (GolfCard[]) testingController.getPlayerHand(i);

            int playerScore = 0;

            for (int j = 0, k = 3; j < 3 && k < 6; j++, k++) {

                if (!(hand[j].getCardFace() == CardFace.UP && hand[k].getCardFace() == CardFace.UP
                && hand[j].getNumber() == hand[k].getNumber())) {
                    playerScore += hand[j].getCardFace() == CardFace.UP ? hand[j].getGolfValue() : 0;
                    playerScore += hand[k].getCardFace() == CardFace.UP ? hand[k].getGolfValue() : 0;
                }
            }

            Truth.assertThat(testingController.getPlayerScores()[i]).isEqualTo(playerScore);
        }
    }

    @Test
    void checkAllUp() {
        testingController.setNumberOfPlayers(2);
        testingController.setPlayerHands();
        Truth.assertThat(testingController.checkAllUp()).isFalse();
        GolfCard[] testingArray = testingController.getPlayerHand(1);
        for(int i = 0; i < 6; i++) {
            testingArray[i].setCardFace(CardFace.UP);
        }
        Truth.assertThat(testingController.checkAllUp()).isTrue();
    }
}
