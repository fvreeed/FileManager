package org.green;

import org.green.handler.FileHandler;
import org.green.handler.UserInput;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String input;

        UserInput userInput;

        FileHandler file;

        do {
            input = scanner.nextLine();
            userInput = new UserInput(input);

            while (!userInput.correct(userInput)) {
                System.out.println("Incorrect input in main");
                input = scanner.nextLine();
                userInput = new UserInput(input);
            }

             file = new FileHandler(userInput);


        } while (!input.equals("quit"));
    }

}