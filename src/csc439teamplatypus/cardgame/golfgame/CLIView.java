package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;

import java.util.Scanner;

public class CLIView extends View {

    Scanner input;

    public CLIView() {

        this.input = new Scanner(System.in);
    }

    public int promptForDiscard() {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.print("Enter the index of the card you would like to discard: ");
            int cardToDiscard = input.nextInt();

            if (cardToDiscard < 0 || cardToDiscard > 5)
                System.out.println(cardToDiscard + " is not a valid index. Please enter a number between 0 & 5");

            else {

                 inputCompleted = true;
                 return cardToDiscard;
            }
        }

        return -1;
    }

    public void chooseDrawSource() {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.println("Would you like to draw from the deck or take the card off the top of the discard pile?");
            System.out.print("Enter \"Deck\" or \"Discard\" (without the quotes: )");
            String decision_deckOrDiscard = input.next();

            switch (decision_deckOrDiscard) {

                case "Deck" -> {

                    Card drawnCard = drawFromPile();
                    System.out.println("You drew the " + drawnCard.getNumber() + " of " + drawnCard.getSuit());
                    System.out.print("Would you like to keep it? Enter \"Yes\" or \"No\": ");
                    String decision_keepOrDiscard = input.next();

                    switch (decision_keepOrDiscard) {

                        case "Yes" -> {

                            int cardToDiscard = promptForDiscard();
                            discardHeldCard(getCurrentPlayerNumber(), cardToDiscard);
                            getCurrentPlayerHand()[cardToDiscard] = drawnCard;
                            inputCompleted = true;
                        }

                        case "No" -> {

                            discardUnheldCard(drawnCard);
                            System.out.println("Card discarded");
                            inputCompleted = true;
                        }
                    }
                }

                case "Discard" -> {

                    drawDiscard(getCurrentPlayerNumber(), promptForDiscard());
                    inputCompleted = true;
                }

                default -> { System.out.println("Could not parse input: " + decision_deckOrDiscard); }
            }
        }
    }

    @Override
    protected void setNumberOfPlayers(int numberOfPlayers) {

    }


    protected void printTopOfDiscardPile() {

        Card topCard = viewTopOfDiscardPile();
        System.out.println("The top card on the discard pile is the " + topCard.getNumber()
                + " of " + topCard.getSuit());
    }
}
