package csc439teamplatypus.cardgame.golfgame;

import com.google.common.truth.Truth;
import csc439teamplatypus.cardgame.CardFace;
import csc439teamplatypus.cardgame.CardNumber;
import csc439teamplatypus.cardgame.CardSuit;
import org.junit.jupiter.api.Test;

public class TestGolfCard {

    GolfCard testingCard;

    @Test
    void getGolfValue_returnsCorrectValue() {

        testingCard = new GolfCard(CardSuit.DIAMOND, CardNumber.QUEEN, CardFace.UP);
        Truth.assertThat(testingCard.getGolfValue()).isEqualTo(10);
    }

}
