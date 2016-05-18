package com.game.tictactoe;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.game.tictactoe.actor.GameRunner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private GameRunner runner;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    public void run(String... args) throws IOException {
        runner.play();
    }

}
