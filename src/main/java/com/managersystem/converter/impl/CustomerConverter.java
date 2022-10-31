package com.managersystem.converter.impl;

import com.managersystem.converter.Converter;
import com.managersystem.model.dao.CustomerDao;
import com.managersystem.model.dto.CustomerDto;

public class CustomerConverter implements Converter<CustomerDao, CustomerDto> {
    @Override
    public CustomerDto convertTo(CustomerDao dao) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(dao.getId());
        customerDto.setName(dao.getName());
        customerDto.setBudget(dao.getBudget());

        return customerDto;
    }

    @Override
    public CustomerDao convertFrom(CustomerDto dto) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setId(dto.getId());
        customerDao.setName(dto.getName());
        customerDao.setBudget(dto.getBudget());

        return customerDao;
    }
}
