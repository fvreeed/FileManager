package org.green.handler;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Scanner;

@Getter
@Setter
public class FileHandler {

    public FileHandler(UserInput userInput) throws IOException {

        switch (userInput.getOperation()) {
            case "-create" -> createFile(userInput.getPath(), userInput.getText());
            case "-delete" -> deleteFile(userInput.getPath());
            case "-read" -> readFile(userInput.getPath());
            case "-write" -> writeFile(userInput.getPath(), userInput.getText());
        }
    }

    private void createFile(String path, String text) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("File " + path + " created");
        } else {
            System.out.println("File already exist");
        }
        if (text != null)
            writeFile(path, text);
    }

    private void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            System.out.println("File " + path + " deleted");
        } else {
            System.out.println("File doesn't exist");
        }
    }

    private void readFile(String path) {
        try {
            String output;
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output = scanner.nextLine();
                System.out.println(output);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void writeFile(String path, String text) {
        StringBuilder newText = new StringBuilder();
        for (int i = 1; i < text.length() - 1; i++) {
            newText.append(text.toCharArray()[i]);
        }
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.append(newText);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
