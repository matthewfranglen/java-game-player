package com.game.tictactoe.actor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.game.tictactoe.data.Settings;
import com.game.tictactoe.data.Tile;

@Component
public class SettingsCollector {

    public Settings collect() throws IOException {
        return new Settings(getTile(), moveFirst(), canWin());
    }

    private Tile getTile() throws IOException {
        System.out.println("Welcome to Tic-Tac-Toe. Would you like to be X or O?");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = reader.readLine().toUpperCase();
            try {
                return Tile.valueOf(input);
            }
            catch (IllegalArgumentException e) {
                System.err.println("Please enter either X or O");
            }
        }
    }

    private boolean moveFirst() throws IOException {
        System.out.println("Would you like to go first? Y/N");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = reader.readLine().toUpperCase();
            if (input.equals("Y")) {
                return true;
            }
            if (input.equals("N")) {
                return false;
            }
            System.err.println("Please enter either Y or N");
        }
    }

    private boolean canWin() throws IOException {
        System.out.println("Would you like to win? Y/N");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = reader.readLine().toUpperCase();
            if (input.equals("Y")) {
                return true;
            }
            if (input.equals("N")) {
                return false;
            }
            System.err.println("Please enter either Y or N");
        }
    }

}
