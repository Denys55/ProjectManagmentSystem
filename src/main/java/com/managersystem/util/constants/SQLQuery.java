package com.managersystem.util.constants;

public class SQLQuery {
    public static final int FIRST_COLUMN = 1;

    //CRUD Operation
    //sql query for company
    public static final String INSERT_COMPANY = "INSERT INTO COMPANIES (id, name, country) values(default, ?, ?)";
    public static final String UPDATE_COMPANY_BY_ID = "UPDATE COMPANIES SET name=?, country=? WHERE id = ?";
    public static final String DELETE_COMPANY_BY_ID = "DELETE FROM COMPANIES WHERE id=?";
    public static final String SELECT_COMPANY_BY_ID = "SELECT id, name, country FROM COMPANIES WHERE id=?";
    public static final String SELECT_ALL_COMPANIES = "SELECT id, name, country FROM COMPANIES";

    //sql query for developer
    public static final String INSERT_DEVELOPER = "INSERT INTO DEVELOPERS (id, name, age, salary) values(default, ?, ?, ?)";
    public static final String UPDATE_DEVELOPER_BY_ID = "UPDATE DEVELOPERS SET name=?, age=?, salary=? WHERE id = ?";
    public static final String DELETE_DEVELOPER_BY_ID = "DELETE FROM DEVELOPERS WHERE id=?";
    public static final String SELECT_DEVELOPER_BY_ID = "SELECT id, name, age, salary FROM DEVELOPERS WHERE id=?";
    public static final String SELECT_ALL_DEVELOPERS = "SELECT id, name, age, salary FROM DEVELOPERS";

    //sql query for customer
    public static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMERS (id, name, budget) values(default, ?, ?)";
    public static final String UPDATE_CUSTOMER_BY_ID = "UPDATE CUSTOMERS SET name=?, budget=? WHERE id = ?";
    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM CUSTOMERS WHERE id=?";
    public static final String SELECT_CUSTOMER_BY_ID = "SELECT id, name, budget FROM CUSTOMERS WHERE id=?";
    public static final String SELECT_ALL_CUSTOMERS = "SELECT id, name, budget FROM CUSTOMERS";

    //sql query for project
    public static final String INSERT_PROJECT = "INSERT INTO PROJECTS (id, name, complexity, cost) values(default, ?, ?, ?)";
    public static final String UPDATE_PROJECT_BY_ID = "UPDATE PROJECTS SET name=?, complexity=?, cost=? WHERE id = ?";
    public static final String DELETE_PROJECT_BY_ID = "DELETE FROM PROJECTS WHERE id=?";
    public static final String SELECT_PROJECT_BY_ID = "SELECT id, name, complexity, cost FROM PROJECTS WHERE id=?";
    public static final String SELECT_ALL_PROJECTS = "SELECT id, name, complexity, cost FROM PROJECTS";

    //sql query for skill
    public static final String INSERT_SKILL = "INSERT INTO SKILLS (id, languages, level) values(default, ?, ?)";
    public static final String UPDATE_SKILL_BY_ID = "UPDATE SKILLS SET languages=?, level=? WHERE id = ?";
    public static final String DELETE_SKILL_BY_ID = "DELETE FROM SKILLS WHERE id=?";
    public static final String SELECT_SKILL_BY_ID = "SELECT id, languages, level FROM SKILLS WHERE id=?";
    public static final String SELECT_ALL_SKILLS = "SELECT id, languages, level FROM SKILLS";

    //sql query for task
    public static final String SUM_SALARY_DEVELOPERS_BY_PROJECT = "SELECT SUM(dev.salary) FROM developers dev\n" +
            "join developers_projects dev_pr on dev.id = dev_pr.developers_id\n" +
            "join projects pr on pr.id = dev_pr.projects_id\n" +
            "where pr.name like ?";

    public static final String DEVELOPERS_BY_PROJECT_NAME = "SELECT dev.id, dev.name, dev.age, dev.salary FROM developers dev\n" +
            "join developers_projects dev_pr on dev.id = dev_pr.developers_id\n" +
            "join projects pr on pr.id = dev_pr.projects_id\n" +
            "where pr.name like ?";

    public static final String DEVELOPERS_BY_SKILL_LEVEL = "SELECT dev.id, dev.name, dev.age, dev.salary FROM developers dev\n" +
            "join developers_skills dev_sk on dev.id = dev_sk.developers_id\n" +
            "join skills sk on sk.id = dev_sk.skills_id\n" +
            "where sk.level like ?";

    public static final String ALL_PROJECTS_WITH_COUNT_DEVELOPERS = "SELECT pr.name, COUNT(dev_pr.developers_id) FROM projects pr\n" +
            "join developers_projects dev_pr on dev_pr.projects_id = pr.id\n" +
            "group by  pr.name";

    private SQLQuery() {
    }
}
