package com.managersystem.command;

import com.managersystem.command.impl.AllProjectsWithCountDevelopersCommand;
import com.managersystem.command.impl.GetDevelopersByProjectNameCommand;
import com.managersystem.command.impl.GetDevelopersBySkillCommand;
import com.managersystem.command.impl.GetSumDevelopersSalaryByProjectCommand;
import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.service.impl.DeveloperService;
import com.managersystem.service.impl.ProjectService;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private Map<String, Command> commands;
    private final DatabaseManagerConnector managerConnector;

    public CommandContainer(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
        DeveloperService developerService = new DeveloperService(managerConnector);
        ProjectService projectService = new ProjectService(managerConnector);

        commands = new HashMap<>();
        commands.put("summa", new GetSumDevelopersSalaryByProjectCommand(projectService));
        commands.put("project_developers", new GetDevelopersByProjectNameCommand(developerService));
        commands.put("skill_developers", new GetDevelopersBySkillCommand(developerService));
        commands.put("project_count_dev", new AllProjectsWithCountDevelopersCommand(projectService));
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }
}
