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

    /**
     * This is the default call for the Card object that requires these two parameters to be input for future testing
     * @param suit Tells what type of suit the Card object is
     * @param number Tells what number the Card object is
     */
    public Card(CardSuit suit, CardNumber number) {
        this.suit = suit;
        this.number = number;
    }
}
