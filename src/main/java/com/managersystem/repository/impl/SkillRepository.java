package com.managersystem.repository.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.model.dao.CompanyDao;
import com.managersystem.model.dao.SkillDao;
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

public class SkillRepository implements Repository<SkillDao> {
    private final DatabaseManagerConnector managerConnector;

    public SkillRepository(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
    }

    @Override
    public Optional<SkillDao> save(SkillDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.INSERT_SKILL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLanguage());
            statement.setString(2, entity.getLevel());
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
    public boolean update(SkillDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.UPDATE_SKILL_BY_ID);
            statement.setString(1, entity.getLanguage());
            statement.setString(2, entity.getLevel());
            statement.setInt(3, entity.getId());
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
    public boolean delete(SkillDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DELETE_SKILL_BY_ID);
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
    public Optional<SkillDao> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.SELECT_SKILL_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return Optional.of(mapSkillFromResultSet(resultSet));
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
    public List<SkillDao> findAll() {
        List<SkillDao> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_SKILLS);
            while (resultSet.next()) {
                result.add(mapSkillFromResultSet(resultSet));
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

    private SkillDao mapSkillFromResultSet(ResultSet resultSet) throws SQLException {
        SkillDao skillDao = new SkillDao();
        skillDao.setId(resultSet.getInt(ColumnName.ID));
        skillDao.setLanguage(resultSet.getString(ColumnName.LANGUAGE));
        skillDao.setLevel(resultSet.getString(ColumnName.LEVEL));

        return skillDao;
    }
}
