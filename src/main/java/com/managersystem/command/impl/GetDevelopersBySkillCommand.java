package com.managersystem.command.impl;

import com.managersystem.command.Command;
import com.managersystem.model.dto.DeveloperDto;
import com.managersystem.service.impl.DeveloperService;

import java.util.List;
import java.util.Scanner;

public class GetDevelopersBySkillCommand implements Command {
    private final DeveloperService service;
    private static final String WRITE_SKILL = "Write skill level.";

    public GetDevelopersBySkillCommand(DeveloperService service) {
        this.service = service;
    }

    @Override
    public String execute() {
        System.out.println(WRITE_SKILL);
        Scanner scanner = new Scanner(System.in);
        String skill = scanner.next();
        List<DeveloperDto> developersBySkillLevel = service.getDevelopersBySkillLevel(skill);
        if (!developersBySkillLevel.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Developers from skill ").append(skill).append("\n");
            for (DeveloperDto developer : developersBySkillLevel) {
                stringBuilder.append(developer.toString()).append("\n");
            }

            return stringBuilder.toString();
        }
        return "Not found developers";
    }
}
