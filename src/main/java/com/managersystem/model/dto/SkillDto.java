package com.managersystem.model.dto;

public class SkillDto implements EntityDto<Integer>{
    private Integer id;
    private String language;
    private String level;

    public SkillDto() {
    }

    public SkillDto(Integer id, String language, String level) {
        this.id = id;
        this.language = language;
        this.level = level;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
