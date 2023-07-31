package com.gamecard.gamecard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Create by mnadz
 * On        29/7/2023
 */
public class GameCardController implements Initializable {
    @FXML
    GridPane gameCardUI = new GridPane();

    private static final String[] CARDS = {
            "2@", "2#", "2^", "2*", "3@", "3#", "3^", "3*", "4@", "4#", "4^", "4*",
            "5@", "5#", "5^", "5*", "6@", "6#", "6^", "6*", "7@", "7#", "7^", "7*",
            "8@", "8#", "8^", "8*", "9@", "9#", "9^", "9*", "10@", "10#", "10^", "10*",
            "J@", "J#", "J^", "J*", "Q@", "Q#", "Q^", "Q*", "k@", "k#", "K^", "K*",
            "A@", "A#", "A^", "A*",
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        distributeCards();
    }

    private String distributeCards(){

        // first shuffle the cards in random
        List<String> shuffleAllCards = new ArrayList<>();

        for (String card : CARDS) {
            shuffleAllCards.add(card);
        }
        Collections.shuffle(shuffleAllCards);
        System.out.println("Shuffled cards:" + shuffleAllCards);

        // initialise player
        int numOfPlayers = 4;
        int playerHoldCard = CARDS.length / numOfPlayers;

        List<List<String>> players = new ArrayList<>();

        // add shuffle card for every player
        for (int i = 0; i < numOfPlayers; i++) {
            List<String> playerCards = new ArrayList<>();
            for (int j = i * playerHoldCard; j < (i + 1) * playerHoldCard; j++) {
                playerCards.add(shuffleAllCards.get(j));
            }
            players.add(playerCards);
        }

        // to create UI

        int column = 0;
        int row = 0;

        for (List<String> playerCards : players) {
            gameCardUI.add(new Text("Player " + (row + 1) + ": "), column, row);

            for (String card : playerCards) {
                column++;
                gameCardUI.add(new Text(card + " "), column, row);
            }
            column = 0;
            row++;
        }

        // Logic to count the highest number of cards with the same alphanumeric
        List<String> highestCardSet = new ArrayList<>();
        int winnerIndex = -1;

        for (int i = 0; i < numOfPlayers; i++) {
            List<String> playerCards = players.get(i);
            String cardPrevAlpa = null;
            List<String> currentCardSet = new ArrayList<>();

            for (String card : playerCards) {
                String cardAlphanumericPart = card.substring(0, card.length() - 1);
                if (cardAlphanumericPart.equals(cardPrevAlpa)) {
                    currentCardSet.add(card);
                } else {
                    cardPrevAlpa = cardAlphanumericPart;
                    currentCardSet.clear();
                    currentCardSet.add(card);
                }

                if (currentCardSet.size() > highestCardSet.size()) {
                    highestCardSet.clear();
                    highestCardSet.addAll(currentCardSet);
                    winnerIndex = i;
                }
            }
        }

        // print out the winner + card the winner hold
        if (!highestCardSet.isEmpty()) {
            winnerText.setText("Player " + (winnerIndex + 1) + " wins with the highest card set: " + highestCardSet);
        } else {
            winnerText.setText("No player has a sequence of cards or tie");
        }

        return "Player " + (winnerIndex + 1) + " wins with the highest card set: " + highestCardSet;
    }

    @FXML
    private Text winnerText;

    @FXML
    private void startGame(){
        gameCardUI.getChildren().clear();
        distributeCards();
    }

}
