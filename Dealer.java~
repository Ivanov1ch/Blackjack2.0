/*
// File:             Dealer.java
// Created:          2018/04/05
// Author:           [author]
// Description:      Describe function here.
*/

import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.util.Arrays;

public class Dealer {
  
  public static Stack<Card> deck = new Stack<Card>();

  public static void shuffleDeck(Stack<Card> deck){

    Random random = new Random();

    for(int i = 0; i < 10000; i++){

      int indexToChange = random.nextInt(deck.size() - 1);
      int indexToChangeTo = random.nextInt(deck.size() - 1);

      swap(deck, indexToChange, indexToChangeTo);

    }

  }
  
  public static void initDeck(Stack<Card> deck){
    final int NUM_SUITS = 4;
    final int CARDS_PER_SUIT = 12;
    
    for(int suit = 0; suit < NUM_SUITS; suit++){
      for(int card = 2; card < CARDS_PER_SUIT; card++){
        deck.push(Card.initCard(card, Card.Suit.values()[suit]));
      }
    }
    
  }

  public static void swap(Stack<Card> deck, int indexToChange, int indexToChangeTo) {

    Card temp = deck.get(indexToChange);
    deck.set(indexToChange, deck.get(indexToChangeTo));
    deck.set(indexToChangeTo, temp);

  }
  
  public static List<Card> dealStartingCards(Stack<Card> deck){
    Card cardOne = deck.pop();
    Card cardTwo = deck.pop();
   
    return Arrays.asList(cardOne, cardTwo);
  }

}