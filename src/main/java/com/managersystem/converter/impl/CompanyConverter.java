package com.managersystem.converter.impl;

import com.managersystem.converter.Converter;
import com.managersystem.model.dao.CompanyDao;
import com.managersystem.model.dto.CompanyDto;

public class CompanyConverter implements Converter<CompanyDao, CompanyDto> {

    @Override
    public CompanyDto convertTo(CompanyDao dao) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(dao.getId());
        companyDto.setName(dao.getName());
        companyDto.setCountry(dao.getCountry());

        return companyDto;
    }

    @Override
    public CompanyDao convertFrom(CompanyDto dto) {
        CompanyDao companyDao = new CompanyDao();
        companyDao.setId(dto.getId());
        companyDao.setName(dto.getName());
        companyDao.setCountry(dto.getCountry());

        return companyDao;
    }
}
