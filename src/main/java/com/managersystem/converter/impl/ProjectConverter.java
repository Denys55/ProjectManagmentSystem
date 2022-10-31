package com.managersystem.converter.impl;

import com.managersystem.converter.Converter;
import com.managersystem.model.dao.ProjectDao;
import com.managersystem.model.dto.ProjectDto;

public class ProjectConverter implements Converter<ProjectDao, ProjectDto> {

    @Override
    public ProjectDto convertTo(ProjectDao dao) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(dao.getId());
        projectDto.setName(dao.getName());
        projectDto.setComplexity(dao.getComplexity());
        projectDto.setCost(dao.getCost());

        return projectDto;
    }

    @Override
    public ProjectDao convertFrom(ProjectDto dto) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setId(dto.getId());
        projectDao.setName(dto.getName());
        projectDao.setComplexity(dto.getComplexity());
        projectDao.setCost(dto.getCost());

        return projectDao;
    }
}
