package com.managersystem.service.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.converter.impl.SkillConverter;
import com.managersystem.model.dao.SkillDao;
import com.managersystem.model.dto.SkillDto;
import com.managersystem.repository.impl.SkillRepository;
import com.managersystem.service.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SkillService implements Service<SkillDto> {
    private final DatabaseManagerConnector managerConnector;
    private SkillConverter converter;
    private SkillRepository repository;

    public SkillService(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
        converter = new SkillConverter();
        repository = new SkillRepository(managerConnector);
    }

    @Override
    public Optional<SkillDto> save(SkillDto skill) {
        Optional<SkillDao> skillSaved = repository.save(converter.convertFrom(skill));

        return skillSaved.map(skillDao -> converter.convertTo(skillDao));
    }

    @Override
    public boolean update(SkillDto skill) {
        return repository.update(converter.convertFrom(skill));
    }

    @Override
    public boolean delete(SkillDto skill) {
        return repository.delete(converter.convertFrom(skill));
    }

    @Override
    public Optional<SkillDto> findById(int id) {
        Optional<SkillDao> skillById = repository.findById(id);
        return skillById.map(skillDao -> converter.convertTo(skillDao));
    }

    @Override
    public List<SkillDto> findAll() {
        return repository.findAll().stream()
                .map(skillDao -> converter.convertTo(skillDao))
                .collect(Collectors.toList());
    }
}
