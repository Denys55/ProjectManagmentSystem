package com.managersystem.model.dao;

public class CompanyDao implements EntityDao<Integer> {
    private Integer id;
    private String name;
    private String country;

    public CompanyDao() {
    }

    public CompanyDao(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public CompanyDao(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
