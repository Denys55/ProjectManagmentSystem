package com.managersystem.service.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.converter.impl.DeveloperConverter;
import com.managersystem.model.dao.DeveloperDao;
import com.managersystem.model.dto.DeveloperDto;
import com.managersystem.repository.impl.DeveloperRepository;
import com.managersystem.service.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeveloperService implements Service<DeveloperDto> {
    private final DatabaseManagerConnector managerConnector;
    private DeveloperConverter converter;
    private DeveloperRepository repository;

    public DeveloperService(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
        converter = new DeveloperConverter();
        repository = new DeveloperRepository(managerConnector);
    }

    @Override
    public Optional<DeveloperDto> save(DeveloperDto entity) {
        Optional<DeveloperDao> save = repository.save(converter.convertFrom(entity));
        return save.map(developerDao -> converter.convertTo(developerDao));
    }

    @Override
    public boolean update(DeveloperDto entity) {
        return repository.update(converter.convertFrom(entity));
    }

    @Override
    public boolean delete(DeveloperDto entity) {
        return repository.delete(converter.convertFrom(entity));
    }

    @Override
    public Optional<DeveloperDto> findById(int id) {
        Optional<DeveloperDao> developerById = repository.findById(id);
        return developerById.map(developerDao -> converter.convertTo(developerDao));
    }

    @Override
    public List<DeveloperDto> findAll() {
        return repository.findAll().stream()
                .map(developerDao -> converter.convertTo(developerDao))
                .collect(Collectors.toList());
    }

    public List<DeveloperDto> getDevelopersByProjectName(String projectName) {
        return repository.getDevelopersByProjectName(projectName).stream()
                .map(developerDao -> converter.convertTo(developerDao))
                .collect(Collectors.toList());
    }

    public List<DeveloperDto> getDevelopersBySkillLevel(String skillLevel) {
        return repository.getDevelopersBySkillLevel(skillLevel).stream()
                .map(developerDao -> converter.convertTo(developerDao))
                .collect(Collectors.toList());
    }
}
