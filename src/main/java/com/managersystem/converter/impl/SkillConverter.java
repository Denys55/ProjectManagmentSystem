package com.managersystem.converter.impl;

import com.managersystem.converter.Converter;
import com.managersystem.model.dao.SkillDao;
import com.managersystem.model.dto.SkillDto;

public class SkillConverter implements Converter<SkillDao, SkillDto> {
    @Override
    public SkillDto convertTo(SkillDao dao) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(dao.getId());
        skillDto.setLanguage(dao.getLanguage());
        skillDto.setLevel(dao.getLevel());

        return skillDto;
    }

    @Override
    public SkillDao convertFrom(SkillDto dto) {
        SkillDao skillDao = new SkillDao();
        skillDao.setId(dto.getId());
        skillDao.setLevel(dto.getLevel());
        skillDao.setLanguage(dto.getLanguage());

        return skillDao;
    }
}
