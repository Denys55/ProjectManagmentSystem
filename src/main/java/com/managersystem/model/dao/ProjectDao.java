package com.managersystem.model.dao;

public class ProjectDao implements EntityDao<Integer> {
    private Integer id;
    private String name;
    private String complexity;
    private Integer cost;

    public ProjectDao() {
    }

    public ProjectDao(String name, String complexity) {
        this.name = name;
        this.complexity = complexity;
    }

    public ProjectDao(Integer id, String name, String complexity) {
        this.id = id;
        this.name = name;
        this.complexity = complexity;
    }

    public ProjectDao(Integer id, String name, String complexity, Integer cost) {
        this.id = id;
        this.name = name;
        this.complexity = complexity;
        this.cost = cost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
