package org.green.handler;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Setter
@Getter
public class UserInput {

    private String userInput;

    private String path;

    private String operation;

    private String text;

    public UserInput(String userInput) {

        String[] userArr = userInput.split(" ");

        if (userArr.length == 0) {
            System.out.println("Incorrect Input. Write file path");
            return;
        } else {
            this.path = userArr[0];
        }
        if (userArr.length < 2) {
            System.out.println("Incorrect Input. Write operation");
            return;
        } else {
            this.operation = userArr[1];
        }
        if (this.operation.equals("-delete") && userArr.length > 2) {
            System.out.println("Incorrect Input");
            operation = "Error";
            return;
        }
        if (this.operation.equals("-write") && userArr.length < 3) {
            System.out.println("Incorrect Input");
            operation = "Error";
            return;
        }
        if (userArr.length == 3) {
            this.text = userArr[2];
        }
    }

    private boolean checkInput(String path, String operation) {
        if (path == null)
            return false;
        if (path.length() < 3)
            return false;
        if (!checkPath(path)) {
            System.out.println("Incorrect path");
            return false;
        }
        if (operation == null)
            return false;
        return operation.equals("-create")
                || operation.equals("-delete")
                || operation.equals("-read")
                || operation.equals("-write");
    }

    private boolean checkText(String text) {
        return text.toCharArray()[0] == '"' || text.toCharArray()[text.length() - 1] == '"';
    }

    private boolean checkPath(String path) {
        int lastIndex = path.lastIndexOf("\\");
        StringBuilder checkPath = new StringBuilder();
        for (int i = 0; i < lastIndex; i++) {
            checkPath.append(path.toCharArray()[i]);
        }
        File file = new File(checkPath.toString());
        if (!file.isDirectory())
            return false;
        return  path.toCharArray()[path.length() - 1] == 't'
                && path.toCharArray()[path.length() - 2] == 'x'
                && path.toCharArray()[path.length() - 3] == 't'
                && path.toCharArray()[path.length() - 4] == '.';
    }

    private boolean hasText(UserInput userInput) {
        return userInput.text != null;
    }

    public boolean correct(UserInput input) {
        if (input.hasText(input)) {
            return input.checkInput(input.getPath(), input.getOperation()) && input.checkText(input.getText());
        } else {
            return input.checkInput(input.getPath(), input.getOperation());
        }
    }
}
