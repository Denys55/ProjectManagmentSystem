package com.managersystem.service.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.converter.impl.CustomerConverter;
import com.managersystem.model.dao.CustomerDao;
import com.managersystem.model.dto.CustomerDto;
import com.managersystem.repository.impl.CustomerRepository;
import com.managersystem.service.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService implements Service<CustomerDto> {
    private final DatabaseManagerConnector managerConnector;
    private CustomerConverter converter;
    private CustomerRepository repository;

    public CustomerService(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
        converter = new CustomerConverter();
        repository = new CustomerRepository(managerConnector);
    }

    @Override
    public Optional<CustomerDto> save(CustomerDto entity) {
        Optional<CustomerDao> save = repository.save(converter.convertFrom(entity));
        return save.map(customerDao -> converter.convertTo(customerDao));
    }

    @Override
    public boolean update(CustomerDto entity) {
        return repository.update(converter.convertFrom(entity));
    }

    @Override
    public boolean delete(CustomerDto entity) {
        return repository.delete(converter.convertFrom(entity));
    }

    @Override
    public Optional<CustomerDto> findById(int id) {
        Optional<CustomerDao> byId = repository.findById(id);
        return byId.map(customerDao -> converter.convertTo(customerDao));
    }

    @Override
    public List<CustomerDto> findAll() {
        return repository.findAll().stream()
                .map(customerDao -> converter.convertTo(customerDao))
                .collect(Collectors.toList());
    }
}
