package com.managersystem.service.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.converter.Converter;
import com.managersystem.converter.impl.CompanyConverter;
import com.managersystem.model.dao.CompanyDao;
import com.managersystem.model.dto.CompanyDto;
import com.managersystem.repository.Repository;
import com.managersystem.repository.impl.CompanyRepository;
import com.managersystem.service.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyService implements Service<CompanyDto> {
    private final DatabaseManagerConnector managerConnector;
    private CompanyConverter converter;
    private CompanyRepository repository;

    public CompanyService(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
        converter = new CompanyConverter();
        repository = new CompanyRepository(managerConnector);
    }

    @Override
    public Optional<CompanyDto> save(CompanyDto entity) {
        Optional<CompanyDao> save = repository.save(converter.convertFrom(entity));
        return save.map(companyDao -> converter.convertTo(companyDao));
    }

    @Override
    public boolean update(CompanyDto entity) {
        return repository.update(converter.convertFrom(entity));
    }

    @Override
    public boolean delete(CompanyDto entity) {
        return repository.delete(converter.convertFrom(entity));
    }

    @Override
    public Optional<CompanyDto> findById(int id) {
        Optional<CompanyDao> byId = repository.findById(id);

        return byId.map(companyDao -> converter.convertTo(companyDao));
    }

    @Override
    public List<CompanyDto> findAll() {
        return repository.findAll().stream()
                .map(companyDao -> converter.convertTo(companyDao))
                .collect(Collectors.toList());
    }
}
