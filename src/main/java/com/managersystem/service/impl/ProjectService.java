package com.managersystem.service.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.converter.impl.ProjectConverter;
import com.managersystem.model.dao.ProjectDao;
import com.managersystem.model.dto.ProjectDto;
import com.managersystem.repository.impl.ProjectRepository;
import com.managersystem.service.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectService implements Service<ProjectDto> {
    private final DatabaseManagerConnector managerConnector;
    private ProjectConverter converter;
    private ProjectRepository repository;

    public ProjectService(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
        converter = new ProjectConverter();
        repository = new ProjectRepository(managerConnector);
    }

    @Override
    public Optional<ProjectDto> save(ProjectDto project) {
        Optional<ProjectDao> projectSaved = repository.save(converter.convertFrom(project));
        return projectSaved.map(projectDao -> converter.convertTo(projectDao));
    }

    @Override
    public boolean update(ProjectDto project) {
        return repository.update(converter.convertFrom(project));
    }

    @Override
    public boolean delete(ProjectDto project) {
        return repository.delete(converter.convertFrom(project));
    }

    @Override
    public Optional<ProjectDto> findById(int id) {
        Optional<ProjectDao> projectById = repository.findById(id);
        return projectById.map(projectDao -> converter.convertTo(projectDao));
    }

    @Override
    public List<ProjectDto> findAll() {
        return repository.findAll().stream()
                .map(projectDao -> converter.convertTo(projectDao))
                .collect(Collectors.toList());
    }

    public Integer getSumSalaryDevelopersByProject(String projectName) {
        return repository.getSumSalaryDevelopersForProject(projectName);
    }

    public Map<String, Integer> getProjectNameWithCountDevelopers() {
        return repository.getProjectNameWithCountDevelopers();
    }
}
