/*
// File:             AboutThisGame.java
// Created:          2018/04/04
// Author:           danIv (Daniel Ivanovich)
// Description:      Tell the user about the game.
*/
import javax.swing.*;

public class AboutThisGame {
  public static void main (String[] args){
    JOptionPane.showMessageDialog(null, "Original Game: \n" +
            "Authors: Armaan Vasowalla & Daniel Ivanovich\n\n" +
            "Version 2.0.0 Author:\n" +
            "Daniel Ivanovich\n" +
            "Current Version: 2.0.0", "About Blackjack", JOptionPane.PLAIN_MESSAGE);
  }
}