package csc439teamplatypus.cardgame;

/**
 * CSC 439 Software Testing & Maintenance
 * @author Nathan Flanagan, Ethan Hunt, Abbi Landers, Matthew Roy
 * @version 1.0
 */

public class Card {
    /** Stores the Card's suit */
    private CardSuit suit;
    /** The value of the Card. Face cards use the name (ex: King is KING, not a numerical value) */
    private CardNumber number;
    /** Whether the card is face-up or face-down */
    private CardFace face;

    /** Creates a new Card
     * @param suit Tells what type of suit the Card object is
     * @param number Tells what number the Card object is
     */
    public Card(CardSuit suit, CardNumber number, CardFace face) {
        this.suit = suit;
        this.number = number;
        this.face = face;
    }

    /** Returns the Card's suit
     * @return suit
     * */
    public CardSuit getSuit() {
        return suit;
    }
    /** Returns the card's number
     * @return number
     * */
    public CardNumber getNumber() {
        return number;
    }

    /** Returns the Card's face
     * @return face
     */
    public CardFace getCardFace() { return face; }

    /** Sets the value of the Card's face
     *
     * @param face Whether the card is face-up (CardFace.UP) or face-down (CardFace.DOWN)
     */
    public void setCardFace(CardFace face) {
        this.face = face;
    }

    /** Returns whether a Card's suit is red
     * @param suit
     * @return Whether the suit is red
     */
    public boolean isRed(CardSuit suit) {
        return suit==CardSuit.HEART||suit==CardSuit.DIAMOND;
    }

    /** Returns whether a Card's suit is black
     * @param suit
     * @return Whether the suit is black
     */
    public boolean isBlack(CardSuit suit) {
        return suit==CardSuit.CLUB||suit==CardSuit.SPADE;
    }
}
