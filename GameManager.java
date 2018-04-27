/*
// File:             GameManager.java
// Created:          2018/04/06
// Author:           daniv (Daniel Ivanovich)
// Description:      Contains all the general functions to run the game.
*/

import javax.swing.*;
import java.util.Scanner;

public class GameManager {

    public static double getMoney() {
        double money = 0;

        while (!false) {
            money = Double.parseDouble((String)JOptionPane.showInputDialog(null, "How much money do you have? You must have at least 1 dollar to play.", "Player info", JOptionPane.PLAIN_MESSAGE, null, null, null));

            String text = Double.toString(Math.abs(money));
            int integerPlaces = text.indexOf('.');
            int decimalPlaces = text.length() - integerPlaces - 1;

            if (money >= 1.00 && decimalPlaces <= 2) {
                break;
            }

            JOptionPane.showMessageDialog(null, "You do not have enough money to begin or entered an invalid amount. Please get more money or check your decimals and try again.", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return money;
    }

    public static String getName() {
        String name;

        while (!false) {
            name = (String)JOptionPane.showInputDialog(null, "Welcome to the table, what is your name?", "Player Info", JOptionPane.PLAIN_MESSAGE, null, null, "");

            if (name.length() >= 3) {
                break;
            }

            JOptionPane.showMessageDialog(null, "Sorry, but '" + name + "' is not a valid name.", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return name;
    }

    public static double getWager(double minWager, double maxWager) {
        double wager;
        while (!false) {
            wager = Double.parseDouble((String)JOptionPane.showInputDialog(null, "What would you like to wager on this hand?", "Wager", JOptionPane.PLAIN_MESSAGE, null, null, null));

            String text = Double.toString(Math.abs(wager));
            int integerPlaces = text.indexOf('.');
            int decimalPlaces = text.length() - integerPlaces - 1;

            if (wager >= minWager && decimalPlaces <= 2 && !(wager > maxWager)) {
                break;
            }

            JOptionPane.showMessageDialog(null, "Sorry, but $" + wager + " is not a valid wager.\nPlease either match or raise $" + minWager + ", and wager less than $" + maxWager + ".", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return wager;

    }

    public static double getInsuranceWager(double minWager, double maxWager) {
        double wager;
        int answer = -9999;
            answer = JOptionPane.showConfirmDialog(null, "Would you like to take insurance?", "Insurance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        while (true) {
            if (answer == JOptionPane.YES_OPTION) {
                wager = Double.parseDouble((String)JOptionPane.showInputDialog(null, "What is your insurance wager?", "Insurance", JOptionPane.PLAIN_MESSAGE, null, null, null));

                String text = Double.toString(Math.abs(wager));
                int integerPlaces = text.indexOf('.');
                int decimalPlaces = text.length() - integerPlaces - 1;

                if (wager >= minWager && decimalPlaces <= 2 && !(wager > maxWager)) {
                    break;
                }

                JOptionPane.showMessageDialog(null, "Sorry, but $" + wager + " is not a valid wager.\nPlease either match or raise $" + minWager + ", and wager less than $" + maxWager + ".", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                return 0.0;
            }
        }

        return wager;

    }

    public static String getChoice() {
        String choice;
        while (true) {
            choice = (String)JOptionPane.showInputDialog(null, "(H)it, (S)tand, (D)ouble, or (Su)rrender", "What do you want to do?", JOptionPane.QUESTION_MESSAGE);

            String partOne = Character.toString(choice.charAt(0)).toUpperCase();
            choice = partOne + choice.substring(1);

            boolean accepted = false;

            String[] acceptedAnswers = {"H", "S", "D", "Su"};
            for (int i = 0; i < acceptedAnswers.length; i++) {
                if (choice.equals(acceptedAnswers[i])) {
                    accepted = true;
                    break;
                }
            }

            if (accepted)
                break;

           JOptionPane.showMessageDialog(null, "Sorry, but '" + choice + "' is not an accepted input.", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return choice;
    }

    public static boolean softNumber(Hand givenHand, int targetNumber) {
        boolean hasAce = false;
        for(int i=0; i < givenHand.hand.size(); i++) {
            if(givenHand.hand.get(i).name.equals("Ace")){
                hasAce = true;
                break;
            }
        }
        if(hasAce) {
            int handPoints = Hand.getPoints(givenHand);              //Forces the Hand class to correctly assign acePoints
            if (Hand.acePoints == 11) {
                //It's a soft hand
                if (handPoints == targetNumber) {
                    return true;
                } else {
                    //The soft hand doesn't add up
                    return false;
                }
            } else {
                //The ace is being valued as a 1 (It's a hard hand)
                return false;
            }
        }
        else{
            //No ace
            return false;
        }

    }

    public static boolean checkForBlackjack(Hand givenHand) {
        if (Hand.getPoints(givenHand) == 21 && givenHand.hand.size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkForBust(Hand givenHand) {
        if (Hand.getPoints(givenHand) > 21) {
            return true;
        } else {
            return false;
        }
    }
}
