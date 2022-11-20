package com.managersystem.util.constants;

import com.managersystem.service.impl.CompanyService;

//Class include just constant attribute name
public class Attribute {

    private Attribute() {
    }

    //ServletContext Attrubute
    public static final String COMPANY_SERVICE = "companyService";
    public static final String CUSTOMER_SERVICE = "customerService";
    public static final String DEVELOPER_SERVICE = "developerService";
    public static final String PROJECT_SERVICE = "projectService";
    public static final String SKILL_SERVICE = "skillService";

    //Request attribute
    public static final String COMPANIES = "companies";
    public static final String CUSTOMERS = "customers";
    public static final String DEVELOPERS = "developers";
    public static final String PROJECTS = "projects";
    public static final String SKILLS = "skills";

}
