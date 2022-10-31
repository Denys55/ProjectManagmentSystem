package com.managersystem.converter.impl;

import com.managersystem.converter.Converter;
import com.managersystem.model.dao.DeveloperDao;
import com.managersystem.model.dto.DeveloperDto;

public class DeveloperConverter implements Converter<DeveloperDao, DeveloperDto> {

    @Override
    public DeveloperDto convertTo(DeveloperDao dao) {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(dao.getId());
        developerDto.setName(dao.getName());
        developerDto.setAge(dao.getAge());
        developerDto.setSalary(dao.getSalary());

        return developerDto;
    }

    @Override
    public DeveloperDao convertFrom(DeveloperDto dto) {
        DeveloperDao developerDao = new DeveloperDao();
        developerDao.setId(dto.getId());
        developerDao.setName(dto.getName());
        developerDao.setAge(dto.getAge());
        developerDao.setSalary(dto.getSalary());

        return developerDao;
    }
}
