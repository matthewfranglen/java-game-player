package com.game.tictactoe.actor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.tictactoe.data.Game;
import com.game.tictactoe.data.Settings;

@Component
public class GameRunner {

    @Autowired
    private MoveMaker mover;

    @Autowired
    private SettingsCollector collector;

    @Autowired
    private HardAI hard;

    @Autowired
    private EasyAI easy;

    public void play() throws IOException {
        Settings settings = collector.collect();
        AI opponent = settings.isWinnable() ? easy : hard;
        Game game = new Game(settings.getPlayerTile(), opponent);

        while (game.isNotOver()) {
            mover.move(game);
        }

        if (game.isWon()) {
            System.out.println("You won!");
        }
        else if(game.isLost()) {
            System.out.println("You suck!");
        }
        else {
            System.out.println("You still suck!");
        }
    }

}
