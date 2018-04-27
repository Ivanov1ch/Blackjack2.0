/*
// File:             MainMenu.java
// Created:          2018/04/04
// Author:           daniv (Daniel Ivanovich)
// Description:      The main menu for the Blackjack game.
*/

import javax.swing.*;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class MainMenu {
    public static void main(String[] args) throws IOException {
        URL menuIconURL = new URL("https://images-na.ssl-images-amazon.com/images/I/81w49Xr-7QL.png");

        ImageIcon mainMenuIcon = new ImageIcon();

        try {
            Image mainMenuImage = ImageIO.read(menuIconURL);
            mainMenuIcon = new ImageIcon(mainMenuImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[] playOptions = {"Play Blackjack", "How to Play Blackjack", "About this Game"};

        while (true) {
            String choice = (String) JOptionPane.showInputDialog(null,
                    "Welcome to the Java Casino!\nWhat would you like to do today?",
                    "Java Casino",
                    JOptionPane.PLAIN_MESSAGE,
                    mainMenuIcon,
                    playOptions,
                    playOptions[0]);

            if (choice != null) {
                if (choice.length() > 0) {
                    runFromChoice(choice, playOptions);
                } else {
                    System.exit(10);
                }
            } else {
                System.exit(10); //10 = invalid choice
            }
        }
    }

    public static void runFromChoice(String choice, Object[] playOptions) {
        if (choice.equals(playOptions[0]))
            SinglePlayer.runGame();
        else if (choice.equals(playOptions[1]))
            HowToPlay.main(null);
        else if (choice.equals(playOptions[2]))
            AboutThisGame.main(null);
    }
}