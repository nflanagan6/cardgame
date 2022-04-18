package csc439teamplatypus.cardgame;

/**
 * CSC 439 Software Testing & Maintenance
 * @author Nathan Flanagan, Ethan Hunt, Abbi Landers, Matthew Roy
 * @version 1.0
 */

public class Card {
    /**
     * Two private variables for CardSuit and CardNumber for the Card object to use in creation
     */
    private CardSuit suit;
    private CardNumber number;
    private CardFace face;

    /**
     * This is the default call for the Card object that requires these two parameters to be input for future testing
     * @param suit Tells what type of suit the Card object is
     * @param number Tells what number the Card object is
     */
    public Card(CardSuit suit, CardNumber number, CardFace face) {
        this.suit = suit;
        this.number = number;
        this.face = face;
    }

    /**
     * Two simple get methods to return the cards suit and number as well as face
     */
    public CardSuit getSuit() {
        return suit;
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardFace getCardFace() { return face; }


    /**
     * Simple set method for the face of the card based on face parameter
     * @param face
     */
    public void setCardFace(CardFace face) {
        this.face = face;
    }

    /**
     * Two methods that can be called on inside of tests to check if the created card is a red card or a black card.
     * @param suit
     */
    public boolean isRed(CardSuit suit) {
        switch(suit) {
            case DIAMOND: case HEART:
                return true;
            default:
                return false;
        }
    }

    public boolean isBlack(CardSuit suit) {
        switch(suit) {
            case CLUB: case SPADE:
                return true;
            default:
                return false;
        }
    }
}