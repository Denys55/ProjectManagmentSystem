package com.managersystem;

import com.managersystem.command.CommandContainer;
import com.managersystem.config.databaseconnection.DatabaseManagerConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private DatabaseManagerConnector managerConnector;
    private CommandContainer commandContainer;
    private Scanner scanner = new Scanner(System.in);
    private static final String MENU = "Choose command and write in console:\n" +
            "1. summa - get developers salary for some project\n" +
            "2. project_developers - get developers from some project\n" +
            "3. skill_developers - get developers by skill level" +
            "4. project_count_dev - get all projects with count developers" +
            "of you want see menu again write - menu";
    private static final String STOP_PROGRAM = "stop";

    public void startProgram() {
        managerConnector = new DatabaseManagerConnector();
        commandContainer = new CommandContainer(managerConnector);
        System.out.println(MENU);
        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase(STOP_PROGRAM)) {
                break;
            }

            if (input.equalsIgnoreCase("menu")) {
                System.out.println(MENU);
            }
            try {
                System.out.println(commandContainer.getCommand(input).execute());
            } catch (NullPointerException exception) {
                System.out.println("You write wrong command");
                System.out.println(MENU);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        main.startProgram();
    }
}
