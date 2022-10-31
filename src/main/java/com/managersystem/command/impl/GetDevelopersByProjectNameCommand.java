package com.managersystem.command.impl;

import com.managersystem.command.Command;
import com.managersystem.model.dto.DeveloperDto;
import com.managersystem.service.impl.DeveloperService;
import com.managersystem.service.impl.ProjectService;

import java.util.List;
import java.util.Scanner;

public class GetDevelopersByProjectNameCommand implements Command {
    private final DeveloperService service;
    private static final String WRITE_PROJECT = "Please write project name.";

    public GetDevelopersByProjectNameCommand(DeveloperService service) {
        this.service = service;
    }

    @Override
    public String execute() {
        System.out.println(WRITE_PROJECT);
        Scanner scanner = new Scanner(System.in);
        String projectName = scanner.next();
        List<DeveloperDto> developersByProjectName = service.getDevelopersByProjectName(projectName);
        if (!developersByProjectName.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Developers from project ").append(projectName).append("\n");
            for (DeveloperDto developer : developersByProjectName) {
                stringBuilder.append(developer.toString()).append("\n");
            }

            return stringBuilder.toString();
        }
        return "Not found developers";
    }
}
