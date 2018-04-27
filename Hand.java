/*
// File:             Hand.java
// Created:          2018/04/05
// Author:           danIv (Daniel Ivanovich)
// Description:      The class for a hand of cards.
*/

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> hand;
    public boolean active = true;
    public static int acePoints;                    //How many points the Ace is being counted as (1/11), used for calculating if a hand is soft or hard.

    Hand(ArrayList<Card> cards) {
        this.hand = cards;
    }

    public static int getPoints(Hand inputtedHand) {
        int points = 0;
        boolean hasAnAce = false;

        for (int i = 0; i < inputtedHand.hand.size(); i++) {
            if (!inputtedHand.hand.get(i).name.equals("Ace")) {
                if ((!inputtedHand.hand.get(i).name.equals("King")) && (!inputtedHand.hand.get(i).name.equals("Queen")) && (!inputtedHand.hand.get(i).name.equals("Jack")))
                    points += Integer.parseInt(inputtedHand.hand.get(i).name);
                else
                    points += 10;
            } else {
                hasAnAce = true;
            }
        }
        if (hasAnAce) {
            Hand tempHand = copyHandWithoutAces(copyHand(inputtedHand));
            if (tempHand.hand.size() == 0) {
                //inputtedHand.hand.stream().forEach(System.out::println);
                points = (11 + inputtedHand.hand.size() - 1);
                //System.out.println(points);
            } else if (points <= 10) {
                points += 11;
                acePoints = 11;
            } else {
                points += 1;
                acePoints = 1;
            }
        }

        return points;
    }

    public static Hand copyHand(Hand inputtedHand) {
        ArrayList<Card> tempList = new ArrayList<>();
        for (int i = 0; i < inputtedHand.hand.size(); i++) {
            tempList.add(Card.copyCard(inputtedHand.hand.get(i)));
        }

        Hand tempHand = new Hand(tempList);

        return tempHand;
    }

    public static Hand copyHandWithoutAces(Hand inputtedHand) {
        ArrayList<Card> tempList = new ArrayList<>();
        for (int i = 0; i < inputtedHand.hand.size(); i++) {
            tempList.add(Card.copyCard(inputtedHand.hand.get(i)));
        }

        List<Card> removeList = new ArrayList<Card>();

        for (Card card : tempList) {
            if (card.name.equals("Ace"))
                removeList.add(card);
        }

        for (Card card : removeList)
            tempList.remove(card);

        Hand tempHand = new Hand(tempList);

        return tempHand;
    }

    public static String printFullHand(Hand inputtedHand) {
        String result = "";
        for (Card card : inputtedHand.hand) {
            result += card.suit.symbol;
            result += card.name;
            result += ", ";
        }

        result.substring(0, result.length() - 1);

        return result;
    }

    public static Hand clearHand(Hand inputtedHand) {
        inputtedHand.hand.clear();

        return inputtedHand;
    }
}