/*
// File:             HowToPlay.java
// Created:          2018/04/04
// Author:           danIv (Daniel Ivanovich)
// Description:      Tell the user how to play Blackjack.
*/


import javax.swing.*;

public class HowToPlay {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "The object of blackjack is to beat the dealer.\n" +
                        "To beat the dealer the player must:\n" +
                        "1. Not bust (go over 21 points in one hand). Then, if they do not bust, they must:\n" +
                        "2. Either outscore the dealer OR have the dealer bust.",
                "How to Play Blackjack - Basic Rules", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Blackjack may be played with one to eight decks of 52-card decks. \n" +
                "This game will use six.", "How to Play Blackjack - The Deck", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Aces may be counted as 1 or 11 points\n" +
                "Normal cards are worth as many points as their number (i.e. 9 of diamonds = 9 points)\n" +
                "Tens and face cards count as 10 points.\n" +
                "The value of a hand is the sum of the point values of the individual cards. \n" +
                "Except, a \"blackjack\" is the highest hand, consisting of an ace and any 10-point card, and it outranks all other 21-point hands.", "How to Play Blackjack - Point Counting", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "After the players have bet, the dealer will give two cards to each player and two cards to himself. \n" +
                "One of the dealer cards is dealt face up. The facedown card is called the \"hole card.\"\n" +
                "Play begins with the player to the dealer's left. The following are the choices available to the player:\n" +
                "Stand: Player stands pat with his cards.\n" +
                "Hit: Player draws another card (and more if he wishes). If this card causes the player's total points to exceed 21 (known as \"breaking\" or \"busting\") then he loses.\n" +
                "Double: Player doubles his bet and gets one, and only one, more card.\n" +
                "Surrender: The player forfeits half his wager, keeping the other half, and does not play out his hand. This option is only available on the initial two cards.\n" +
                "\nAfter each player has had his turn, the dealer will turn over his hole card. \n" +
                "If the dealer has 16 or less, then he will draw another card. If he has an ace and another set of cards totaling 6 points, it is said that he has a \"Soft 17\". \n" +
                "In this casino, the dealer is forced to hit on a soft 17.\n" +
                "If the dealer goes over 21 points, then any player who didn't already bust will win.\n" +
                "If the dealer does not bust, then the higher point total between the player and dealer will win.\n" +
                "Winning wagers pay even money, except for blackjack, which pays 3:2", "How to Play Blackjack - Basic Play", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "If the dealer has an ace showing, he will offer a side bet called \"insurance.\"\n" +
                "This side wager pays 2 to 1 if the dealer's hole card is any 10-point card. Insurance wagers are optional.\n" +
                "If the dealer does have a blackjack, then all wagers (except insurance) will lose, unless the player also has a blackjack, which will result in a push (a tie).\n" +
                "The dealer will resolve insurance wagers at this time.", "How to Play Blackjack - Insurance", JOptionPane.QUESTION_MESSAGE);

    }

    //Makes it easier to type it all out.
    public static void println(String string) {
        System.out.println(string);
    }
}