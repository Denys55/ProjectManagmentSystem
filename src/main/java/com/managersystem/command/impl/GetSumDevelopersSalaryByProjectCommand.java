package com.managersystem.command.impl;

import com.managersystem.command.Command;
import com.managersystem.service.impl.ProjectService;

import java.util.Scanner;

public class GetSumDevelopersSalaryByProjectCommand implements Command {
    private final ProjectService service;
    private static final String WRITE_PROJECT = "Please write project name.";

    public GetSumDevelopersSalaryByProjectCommand(ProjectService service) {
        this.service = service;
    }

    @Override
    public String execute() {
        System.out.println(WRITE_PROJECT);
        Scanner scanner = new Scanner(System.in);
        String parameter = scanner.next();
        Integer result = service.getSumSalaryDevelopersByProject(parameter);
        return String.format(("Sum developers salary by project %s = %d"), parameter, result);
    }
}
