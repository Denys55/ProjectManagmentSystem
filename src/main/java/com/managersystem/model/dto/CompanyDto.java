package com.managersystem.model.dto;

public class CompanyDto implements EntityDto<Integer>{
    private Integer id;
    private String name;
    private String country;

    public CompanyDto() {
    }

    public CompanyDto(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
