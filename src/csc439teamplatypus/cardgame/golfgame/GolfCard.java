package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.CardFace;
import csc439teamplatypus.cardgame.CardNumber;
import csc439teamplatypus.cardgame.CardSuit;

public class GolfCard extends Card {

    /** Creates a new GolfCard
     *
     * @param suit   Tells what type of suit the Card object is
     * @param number Tells what number the Card object is
     * @param face   Tells whether the Card is face-up or face-down
     */

    public GolfCard(CardSuit suit, CardNumber number, CardFace face) {
        super(suit, number, face);
    }

    public int getGolfValue() {
        switch (getNumber()) {
            case ACE -> { return 1; }
            case TWO -> { return -2; }
            case THREE -> { return 3; }
            case FOUR -> { return 4; }
            case FIVE -> { return 5; }
            case SIX -> { return 6; }
            case SEVEN -> { return 7; }
            case EIGHT -> { return 8; }
            case NINE -> { return 9; }
            case TEN, JACK, QUEEN -> { return 10; }
            case KING -> { return 0; }
            default -> { return 21; }           // TODO - what should the default return value be?
        }
    }
}
//TODO - Remove for future testing from Card() javadocs
//TODO - add TestGolfCard for getGolfValue() to check.