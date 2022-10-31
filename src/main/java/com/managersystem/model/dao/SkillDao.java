package com.managersystem.model.dao;

public class SkillDao implements EntityDao<Integer>{
    private Integer id;
    private String language;
    private String level;

    public SkillDao() {
    }

    public SkillDao(String language, String level) {
        this.language = language;
        this.level = level;
    }

    public SkillDao(Integer id, String language, String level) {
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
