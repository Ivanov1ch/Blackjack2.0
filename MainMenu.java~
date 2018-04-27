/*
// File:             MainMenu.java
// Created:          2018/04/04
// Author:           daniv (Daniel Ivanovich)
// Description:      The main menu for the Blackjack game.
*/
import java.util.Scanner;

public class MainMenu {
  public static void main (String[] args){
    Scanner scanner = new Scanner(System.in);
    
    int[] acceptedAnswers = {1, 2, 3, 4, 5};
    
    System.out.println("Welcome to the Java Casino!");
    
    //While playing
    while(true){
    
      printMenu();
      if(!runProgram(getInput(scanner, acceptedAnswers))) //It returns false if its time to break, but otherwise runs some code.
        break;
    
    }
    scanner.close();
  }
  
  public static void printMenu(){
    System.out.println("Enter the number to launch the corresponding option. \n");
    System.out.println("1. Blackjack - single player");
    System.out.println("2. Blackjack - multiple players");
    System.out.println("3. Blackjack - how to play");
    System.out.println("4. About this game");
    System.out.println("5. Exit");
  }
  
  public static int getInput(Scanner scanner, int[] acceptedAnswers){
    boolean validInput = false;
    int input = 5;            //Default to exit
    
    while(!validInput){
      input = scanner.nextInt();
      scanner.nextLine();      //Clear the newline character
      
      for(int i = 0; i < acceptedAnswers.length; i++){
        if(input == acceptedAnswers[i]){
          validInput = true;
          return input;        //Don't say the input is invalid.
        }
      }
      
      System.out.println("Sorry, but '" + input + "' is not a valid input.\nPlease enter a number between " + acceptedAnswers[0] + " and " + acceptedAnswers[acceptedAnswers.length] /*The last element*/ + ".");
      
    }
    
    return input;
    
  }
  
  public static boolean runProgram(int input){
    if(input == 5){
      return false;
    }
    else{
      
      /*
      if(input == 1)
        //Run 1 player Blackjack here
      else if(input == 2)
        //Run 2 player Blackjack here
      else if(input == 3)
        HowToPlay.main(null);
      else
        AboutThisGame.main(null);
      */
        
      return true;
    }
  }
}