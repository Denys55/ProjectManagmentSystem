package com.managersystem.model.dto;

public class ProjectDto implements EntityDto<Integer> {
    private Integer id;
    private String name;
    private String complexity;
    private Integer cost;

    public ProjectDto() {
    }

    public ProjectDto(Integer id, String name, String complexity, Integer cost) {
        this.id = id;
        this.name = name;
        this.complexity = complexity;
        this.cost = cost;
    }

    @Override
    public Integer getId() {
        return id;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
