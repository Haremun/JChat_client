package com.bieganski.jchat_client.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUi implements Ui {
    Scanner scanner;
    PrintStream out;

    public ConsoleUi(PrintStream out, InputStream in) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    @Override
    public void printMessage(Object message) {
        out.println(message);
    }

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }
}
