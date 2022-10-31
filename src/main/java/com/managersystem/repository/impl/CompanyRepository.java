package com.managersystem.repository.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.model.dao.CompanyDao;
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

public class CompanyRepository implements Repository<CompanyDao> {
    private final DatabaseManagerConnector managerConnector;

    public CompanyRepository(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
    }

    @Override
    public Optional<CompanyDao> save(CompanyDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
            int result = statement.executeUpdate();
            if (result > 0) {
                resultSet = statement.getGeneratedKeys();
                entity.setId(resultSet.getInt(SQLQuery.FIRST_COLUMN));
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
    public boolean update(CompanyDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.UPDATE_COMPANY_BY_ID);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
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
    public boolean delete(CompanyDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DELETE_COMPANY_BY_ID);
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
    public Optional<CompanyDao> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.SELECT_COMPANY_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return Optional.of(mapCompanyFromResultSet(resultSet));
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
    public List<CompanyDao> findAll() {
        List<CompanyDao> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_COMPANIES);
            while (resultSet.next()) {
                result.add(mapCompanyFromResultSet(resultSet));
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

    private CompanyDao mapCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        CompanyDao company = new CompanyDao();
        company.setId(resultSet.getInt(ColumnName.ID));
        company.setName(resultSet.getString(ColumnName.NAME));
        company.setCountry(resultSet.getString(ColumnName.COUNTRY));
        return company;
    }
}
