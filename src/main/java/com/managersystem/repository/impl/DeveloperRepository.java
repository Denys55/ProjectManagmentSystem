package com.managersystem.repository.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.model.dao.DeveloperDao;
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
import java.util.List;
import java.util.Optional;

public class DeveloperRepository implements Repository<DeveloperDao> {
    private final DatabaseManagerConnector managerConnector;

    public DeveloperRepository(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
    }

    @Override
    public Optional<DeveloperDao> save(DeveloperDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.INSERT_DEVELOPER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setInt(3, entity.getSalary());
            int result = statement.executeUpdate();
            if (result > 0) {
                resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                entity.setId(resultSet.getInt(SQLQuery.FIRST_COLUMN)); }
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
    public boolean update(DeveloperDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.UPDATE_DEVELOPER_BY_ID);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setInt(3, entity.getSalary());
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
    public boolean delete(DeveloperDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DELETE_DEVELOPER_BY_ID);
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
    public Optional<DeveloperDao> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.SELECT_DEVELOPER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return Optional.of(mapDeveloperFromResultSet(resultSet));
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
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_DEVELOPERS);
            while (resultSet.next()) {
                result.add(mapDeveloperFromResultSet(resultSet));
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

    public List<DeveloperDao> getDevelopersByProjectName(String projectName) {
        List<DeveloperDao> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DEVELOPERS_BY_PROJECT_NAME);
            statement.setString(1, projectName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(mapDeveloperFromResultSet(resultSet));
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

    public List<DeveloperDao> getDevelopersBySkillLevel(String skillLevel) {
        List<DeveloperDao> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DEVELOPERS_BY_SKILL_LEVEL);
            statement.setString(1, skillLevel);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(mapDeveloperFromResultSet(resultSet));
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

    private DeveloperDao mapDeveloperFromResultSet(ResultSet resultSet) throws SQLException {
        DeveloperDao developer = new DeveloperDao();
        developer.setId(resultSet.getInt(ColumnName.ID));
        developer.setName(resultSet.getString(ColumnName.NAME));
        developer.setAge(resultSet.getInt(ColumnName.AGE));
        developer.setSalary(resultSet.getInt(ColumnName.SALARY));
        return developer;
    }
}
