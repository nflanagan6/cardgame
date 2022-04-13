package csc439teamplatypus.cardgame;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;


public class CardTest {
    Card queenClubs = new Card(CardSuit.CLUB, CardNumber.QUEEN, CardFace.UP);
    Card nineHeart = new Card(CardSuit.HEART, CardNumber.NINE, CardFace.DOWN);
    Card fourDiamond = new Card(CardSuit.DIAMOND, CardNumber.FOUR, CardFace.UP);
    Card aceSpade = new Card(CardSuit.SPADE, CardNumber.ACE, CardFace.DOWN);

    @Test
    public void numTest() {
        assertThat(queenClubs.getNumber()).isEqualTo(CardNumber.QUEEN);
        assertThat(nineHeart.getNumber()).isEqualTo(CardNumber.NINE);
        assertThat(fourDiamond.getNumber()).isEqualTo(CardNumber.FOUR);
        assertThat(aceSpade.getNumber()).isEqualTo(CardNumber.ACE);

    }
    @Test
    public void colorTest() {
        //Black card case
        assertThat(queenClubs.isBlack(queenClubs.getSuit()));
        assertThat(aceSpade.isBlack(aceSpade.getSuit()));

        //Red card case
        assertThat(nineHeart.isRed(nineHeart.getSuit()));
        assertThat(fourDiamond.isRed(fourDiamond.getSuit()));

        //Handles case where they aren't equivalent
        assertThat(queenClubs.isRed(queenClubs.getSuit())).isFalse();
        assertThat(nineHeart.isBlack(nineHeart.getSuit())).isFalse();
        assertThat(fourDiamond.isBlack(fourDiamond.getSuit())).isFalse();
        assertThat(aceSpade.isRed(aceSpade.getSuit())).isFalse();
    }

    @Test
    void faceTest() {
        assertThat(queenClubs.getCardFace()).isEqualTo(CardFace.UP);
        assertThat(nineHeart.getCardFace()).isEqualTo(CardFace.DOWN);
        assertThat(fourDiamond.getCardFace()).isEqualTo(CardFace.UP);
        assertThat(aceSpade.getCardFace()).isEqualTo(CardFace.DOWN);
    }
}