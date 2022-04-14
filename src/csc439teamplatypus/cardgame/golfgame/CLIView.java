package csc439teamplatypus.cardgame.golfgame;

import csc439teamplatypus.cardgame.Card;
import csc439teamplatypus.cardgame.*;
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

    public void chooseCardToFlip() {

        boolean inputCompleted = false;

        while (!inputCompleted) {

            System.out.println("Which card would you like to flip over? You can only flip over a face-down card.");
            System.out.print("Enter one of: ");
            for (int i = 0; i < 6; i++)
                if (getCurrentPlayerHand()[i].getCardFace() == CardFace.DOWN)
                    System.out.print(i + " ");
            System.out.print(": ");
            int cardToFlip = input.nextInt();

            if (getCurrentPlayerHand()[cardToFlip].getCardFace() == CardFace.DOWN) {

                flipCard(getCurrentPlayerNumber(), cardToFlip);
                inputCompleted = true;
            }

            else {

                System.out.println("Card number " + cardToFlip + " cannot be flipped over.");
             }

        }
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



    public void nextTurn() {

        System.out.println("It's player " + getCurrentPlayerNumber() + 1 + "'s turn!");

        //Print hand for current player
        //Print card on top of discard pile

        if (cardsRemaining()) {

            boolean inputCompleted = false;

            while (!inputCompleted) {

                if (hasFaceDownCard(getCurrentPlayerNumber())) {

                    System.out.println("Would you like to flip one of your cards over or draw a new card?");
                    System.out.print("Enter \"Draw\" or \"Flip\": ");
                    String decision_DrawOrFlip = input.next();

                    switch (decision_DrawOrFlip) {

                        case "Draw" -> {

                            chooseDrawSource();
                            inputCompleted = true;
                        }

                        case "Flip" -> {

                            chooseCardToFlip();
                            inputCompleted = true;
                        }

                        default -> {
                            System.out.println("Could not parse input " + decision_DrawOrFlip);
                        }
                    }
                }

                else {

                    chooseDrawSource();
                    inputCompleted = true;
                }
            }
        }
    }

    protected void printTopOfDiscardPile() {

        Card topCard = viewTopOfDiscardPile();
        System.out.println("The top card on the discard pile is the " + topCard.getNumber()
                + " of " + topCard.getSuit());
    }
}
