package com.game.tictactoe.actor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.game.tictactoe.data.Game;

@Component
public class MoveMaker {

    public void move(Game game) throws IOException {
        System.out.print("Enter Position as row|number " +
                "(eg the first cell is 00, the one to it's right is 01, the one below it is 10: ");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            int row, column;

            try {
                String position = input.readLine();
                row = Integer.parseInt(position.substring(0, 1));
                column = Integer.parseInt(position.substring(1, 2));
            }
            catch (NumberFormatException e) {
                System.err.println("That is not a valid location. Try again\n");
                continue;
            }

            if (! game.canMove(row, column)) {
                System.err.println("Already a piece there. Try again\n");
                continue;
            }

            game.move(row, column);
        }
    }

}
