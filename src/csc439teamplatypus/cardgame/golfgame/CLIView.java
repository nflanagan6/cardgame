package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;

import java.util.Scanner;

public class CLIView extends View {

    Scanner input;

    public CLIView() {

        this.input = new Scanner(System.in);
    }

    public void promptForDiscard() {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.print("Enter the index of the card you would like to discard: ");
            int cardToDiscard = input.nextInt();

            if (cardToDiscard < 0 || cardToDiscard > 5)
                System.out.println(cardToDiscard + " is not a valid index. Please enter a number between 0 & 5");

            else {

                 inputCompleted = true;
                 discard(getPlayerNumber(), cardToDiscard);
            }
        }
    }

    @Override
    protected Card drawDiscard(Card cardToReplace) {
        return null;
    }

    @Override
    protected Card drawFromPile() {
        return null;
    }

    @Override
    protected void setNumberOfPlayers(int numberOfPlayers) {

    }

    @Override
    protected void viewTopOfDiscardPile() {

        Card topCard = getController().viewTopOfDiscardPile();
        System.out.println("The top card on the discard pile is the " + topCard.getNumber()
                + " of " + topCard.getSuit());
    }
}
