package com.managersystem.command.impl;

import com.managersystem.command.Command;
import com.managersystem.service.impl.DeveloperService;
import com.managersystem.service.impl.ProjectService;

import java.util.Map;

public class AllProjectsWithCountDevelopersCommand implements Command {
    private final ProjectService service;
    private static final String NAME = "project name: ";
    private static final String COUNT = "count developers: ";

    public AllProjectsWithCountDevelopersCommand(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute() {
        Map<String, Integer> projectNameWithCountDevelopers = service.getProjectNameWithCountDevelopers();
        if (!projectNameWithCountDevelopers.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Integer> entry : projectNameWithCountDevelopers.entrySet()) {
                stringBuilder.append(NAME).append(entry.getKey()).append(", ").append(COUNT).append(entry.getValue())
                        .append("\n");
            }
            return stringBuilder.toString();
        }

        return "Error 501";
    }
}
