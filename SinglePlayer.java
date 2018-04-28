/*
// File:             SinglePlayer.java
// Created:          2018/04/05
// Author:           danIv
// Description:      The single player version of Blackjack.
*/

import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class SinglePlayer {
    public static double money;
    public static double wager;
    public static double insuranceWager;

    public static void runGame() throws IOException{
        String name = GameManager.getName();

        money = GameManager.getMoney();
        double startingMoney = money;

        double maxMoneymade = 0.0;

        File topEarnerFile = new File("top earner.txt");

        Scanner topEarnerReader = new Scanner(System.in); //Default to terminal, so we don't get errors when trying to close

        String topEarnerName;
        double topEarned = 0.0;

        if(topEarnerFile.exists()){
             topEarnerReader = new Scanner(topEarnerFile);

             if(topEarnerReader.hasNext()) {

                 topEarnerName = topEarnerReader.nextLine();
                 topEarned = topEarnerReader.nextDouble();

                 JOptionPane.showMessageDialog(null, "Welcome, " + name + ".\n" +
                         "The top earner so far is " + topEarnerName + ".\n" +
                         "They earned $" + topEarned + ".");
             }
        }

        PrintWriter topEarnerWriter = new PrintWriter(topEarnerFile);

        while (true) {
            if (money < 1.00) {
                JOptionPane.showMessageDialog(null, "You don't have enough money to keep playing!", "Uh oh!", JOptionPane.ERROR_MESSAGE);
                break;
            }
            Dealer.resetDeck(Dealer.deck);
            Dealer.initDeck(Dealer.deck);
            Dealer.shuffleDeck(Dealer.deck);

            boolean blackjack = false;
            boolean bust = false;
            boolean playStillGoing = true;

            Hand playerHand = new Hand(new ArrayList<Card>(Dealer.dealStartingCards(Dealer.deck)));
            Dealer.hand = new Hand(new ArrayList<Card>(Dealer.dealStartingCards(Dealer.deck)));


            while (true) {
                wager = GameManager.getWager(1.00, money);
                if (wager <= money) {
                    break;
                }

                JOptionPane.showMessageDialog(null, "You entered more money than you currently have! Please enter a valid wager.", "Error!", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Dealing cards...", "", JOptionPane.PLAIN_MESSAGE);

            JOptionPane.showMessageDialog(null, "You have received a " + playerHand.hand.get(0).suit.symbol + playerHand.hand.get(0).name + " and a " + playerHand.hand.get(1).suit.symbol + playerHand.hand.get(1).name + ".\n" +
                    "The dealer has received a " + Dealer.hand.hand.get(0).suit.symbol + Dealer.hand.hand.get(0).name + " and an unknown card.", "Hand updated!", JOptionPane.PLAIN_MESSAGE);

            if (Dealer.hand.hand.get(0).name.equals("Ace")) {
                while (true) {
                    insuranceWager = GameManager.getInsuranceWager(1.00, wager / 2.0);
                    if (insuranceWager + wager <= money) {
                        break;
                    }

                    JOptionPane.showMessageDialog(null, "you're entering more money than you have to bet! Please wager a value less than or equal to " + (money - wager) + ".", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (GameManager.checkForBlackjack(playerHand)) {
                //Blackjack!
                JOptionPane.showMessageDialog(null, "That's a blackjack! The dealer now must reveal his cards", "", JOptionPane.PLAIN_MESSAGE);
                playStillGoing = false;
                blackjack = true;
            }

            boolean firstPlay = true;

            while (playStillGoing) {
                int choice = GameManager.getChoice();

                if (choice == 0) {
                    Dealer.dealCard(playerHand.hand);
                    JOptionPane.showMessageDialog(null, "You have received a " + playerHand.hand.get(playerHand.hand.size() - 1).suit.symbol + playerHand.hand.get(playerHand.hand.size() - 1).name + ".", "Hand updated!", JOptionPane.PLAIN_MESSAGE);
                } else if (choice == 1) {
                    Dealer.dealCard(playerHand.hand);
                    wager *= 2;
                    JOptionPane.showMessageDialog(null, "Upping your bid to $" + wager, "Wager updated!", JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, "You have received a " + playerHand.hand.get(playerHand.hand.size() - 1).suit.symbol + playerHand.hand.get(playerHand.hand.size() - 1).name + ".", "Hand updated!", JOptionPane.PLAIN_MESSAGE);
                    playStillGoing = false;
                } else if (choice == 2) {
                    playStillGoing = false;
                    break;
                } else {
                    if (firstPlay) {
                        wager *= 0.5;
                        playerHand.active = false;
                        firstPlay = false;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry, but you can only Surrender on your first hand!", "Error!", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                }

                JOptionPane.showMessageDialog(null, "Your current hand is: " + Hand.printFullHand(playerHand) + ".", "Hand info", JOptionPane.PLAIN_MESSAGE);

                firstPlay = false;

                bust = GameManager.checkForBust(playerHand);

                if (bust) {
                    playStillGoing = false;
                }

            }

            if (!blackjack) {
                Dealer.useTurn(Dealer.hand);
            } else {
                Dealer.revealHand();
            }

            getWinner(playerHand, Dealer.hand, blackjack, bust);

            JOptionPane.showMessageDialog(null, "You have $" + money + " left.", "Money updated", JOptionPane.PLAIN_MESSAGE);

            if(money - startingMoney > maxMoneymade){
                maxMoneymade = money - startingMoney;
            }

            /*****RESET******/
            Hand.clearHand(playerHand);
            Hand.clearHand(Dealer.hand);
            insuranceWager = 0;
            wager = 0;

            if (money < 1) {
                JOptionPane.showMessageDialog(null, "You don't have enough money to keep playing!", "Error!", JOptionPane.ERROR_MESSAGE);
                break;
            }

            int answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                //Do nothing
            } else {
                break;
            }
        }

        if(maxMoneymade > topEarned && maxMoneymade > 0.0){
            JOptionPane.showMessageDialog(null, "Congratulations " + name + "!\n" +
                    "You are the Java Arcade's top earner! You earned $" + maxMoneymade + ", beating the old record of $" +topEarned + "!", "New Record!", JOptionPane.PLAIN_MESSAGE);

            topEarnerWriter.println(name);
            topEarnerWriter.print(maxMoneymade);
        }

        topEarnerReader.close();
        topEarnerWriter.close();
    }


    public static void getWinner(Hand playerHand, Hand dealerHand, boolean playerBlackjack, boolean playerBust) {
        if (playerBust) {
            //The player goes first, so it checks if the player busted first.
            JOptionPane.showMessageDialog(null, "You busted! You lose your wager of $" + wager + ".", "", JOptionPane.PLAIN_MESSAGE);
            money -= wager;
            if (!GameManager.checkForBlackjack(dealerHand)) {
                if (insuranceWager != 0.0) {
                    //They took insurance
                    JOptionPane.showMessageDialog(null, "The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!", "", JOptionPane.PLAIN_MESSAGE);
                    money -= insuranceWager;
                }
            } else {
                if (insuranceWager != 0.0) {
                    //They took insurance
                    insuranceWager *= 2;
                    JOptionPane.showMessageDialog(null, "However, you win back 2:1 ($" + insuranceWager + ") from your insurance!", "", JOptionPane.PLAIN_MESSAGE);
                    money += insuranceWager;
                }
            }
        } else if (GameManager.checkForBust(dealerHand)) {
            JOptionPane.showMessageDialog(null, "The dealer busted! You win your wager of $" + wager + ".", "", JOptionPane.PLAIN_MESSAGE);
            money += wager;
            if (insuranceWager != 0.0) {
                //They took insurance
                JOptionPane.showMessageDialog(null, "The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!", "", JOptionPane.PLAIN_MESSAGE);
                money -= insuranceWager;
            }
        } else {
            //Neither busted
            if (playerBlackjack && GameManager.checkForBlackjack(dealerHand)) {
                //2 blackjacks == tie
                JOptionPane.showMessageDialog(null, "It's a push! You don't lose or gain anything!", "", JOptionPane.PLAIN_MESSAGE);
                if (insuranceWager != 0.0) {
                    //They took insurance
                    insuranceWager *= 2;
                    JOptionPane.showMessageDialog(null, "However, you win back 2:1 ($" + insuranceWager + ") from your insurance!", "", JOptionPane.PLAIN_MESSAGE);
                    money += insuranceWager;
                }
            } else if (playerBlackjack) {
                wager *= 1.5;
                JOptionPane.showMessageDialog(null, "You got a blackjack! You win 3:2 on your wager, or $" + wager + ".", "", JOptionPane.PLAIN_MESSAGE);
                money += wager;
                if (insuranceWager != 0.0) {
                    //They took insurance
                    JOptionPane.showMessageDialog(null, "The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!", "", JOptionPane.PLAIN_MESSAGE);
                    money -= insuranceWager;
                }
            } else if (GameManager.checkForBlackjack(dealerHand)) {
                JOptionPane.showMessageDialog(null, "The dealer got a blackjack! You lose your $" + wager + " wager.", "", JOptionPane.PLAIN_MESSAGE);
                money -= wager;
                if (insuranceWager != 0.0) {
                    //They took insurance
                    insuranceWager *= 2;
                    JOptionPane.showMessageDialog(null, "However, you win back 2:1 ($" + insuranceWager + ") from your insurance!", "", JOptionPane.PLAIN_MESSAGE);
                    money += insuranceWager;
                }
            } else {
                //No busts, no blackjacks
                if (insuranceWager != 0.0) {
                    //They took insurance
                    JOptionPane.showMessageDialog(null, "The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!", "", JOptionPane.PLAIN_MESSAGE);
                    money -= insuranceWager;
                }

                if (Hand.getPoints(playerHand) == Hand.getPoints(dealerHand)) {
                    JOptionPane.showMessageDialog(null, "It's a push! You don't lose or gain anything!", "", JOptionPane.PLAIN_MESSAGE);
                } else if (Hand.getPoints(playerHand) > Hand.getPoints(dealerHand)) {
                    JOptionPane.showMessageDialog(null, "You win! You get $" + wager + ".", "", JOptionPane.PLAIN_MESSAGE);
                    money += wager;
                } else {
                    JOptionPane.showMessageDialog(null, "You lose! You lose $" + wager + ".", "", JOptionPane.PLAIN_MESSAGE);
                    money -= wager;
                }
            }
        }
    }

}