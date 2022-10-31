package com.managersystem.model.dao;

public class CustomerDao implements EntityDao<Integer> {
    private Integer id;
    private String name;
    private Integer budget;

    public CustomerDao() {
    }

    public CustomerDao(String name, Integer budget) {
        this.name = name;
        this.budget = budget;
    }

    public CustomerDao(Integer id, String name, Integer budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
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

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
