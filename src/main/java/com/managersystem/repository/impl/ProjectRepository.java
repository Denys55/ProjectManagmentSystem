package com.managersystem.repository.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.model.dao.ProjectDao;
import com.managersystem.repository.Repository;
import com.managersystem.util.DBUtils;
import com.managersystem.util.constants.ColumnName;
import com.managersystem.util.constants.SQLQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectRepository implements Repository<ProjectDao> {
    private final DatabaseManagerConnector managerConnector;

    public ProjectRepository(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
    }

    @Override
    public Optional<ProjectDao> save(ProjectDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.INSERT_PROJECT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getComplexity());
            statement.setInt(3, entity.getCost());
            int result = statement.executeUpdate();
            if (result > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    entity.setId(resultSet.getInt(SQLQuery.FIRST_COLUMN));
                }
            }
            return Optional.of(entity);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(ProjectDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.UPDATE_PROJECT_BY_ID);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getComplexity());
            statement.setInt(3, entity.getCost());
            statement.setInt(4, entity.getId());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return false;
    }

    @Override
    public boolean delete(ProjectDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DELETE_PROJECT_BY_ID);
            statement.setInt(1, entity.getId());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return false;
    }

    @Override
    public Optional<ProjectDao> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.SELECT_PROJECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return Optional.of(mapProjectFromResultSet(resultSet));
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<ProjectDao> findAll() {
        List<ProjectDao> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_PROJECTS);
            while (resultSet.next()) {
                result.add(mapProjectFromResultSet(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return result;
    }

    public Integer getSumSalaryDevelopersForProject(String projectName) {
        List<ProjectDao> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.SUM_SALARY_DEVELOPERS_BY_PROJECT);
            statement.setString(1, projectName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return 0;
    }

    public Map<String, Integer> getProjectNameWithCountDevelopers() {
        Map<String, Integer> result = new HashMap<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.ALL_PROJECTS_WITH_COUNT_DEVELOPERS);
            while (resultSet.next()) {
                result.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);
        }
        return result;
    }

    private ProjectDao mapProjectFromResultSet(ResultSet resultSet) throws SQLException {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setId(resultSet.getInt(ColumnName.ID));
        projectDao.setName(resultSet.getString(ColumnName.NAME));
        projectDao.setComplexity(resultSet.getString(ColumnName.COMPLEXITY));
        projectDao.setCost(resultSet.getInt(ColumnName.COST));

        return projectDao;
    }
}
