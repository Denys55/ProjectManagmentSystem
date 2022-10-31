package com.managersystem.repository.impl;

import com.managersystem.config.databaseconnection.DatabaseManagerConnector;
import com.managersystem.model.dao.CustomerDao;
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

public class CustomerRepository implements Repository<CustomerDao> {
    private final DatabaseManagerConnector managerConnector;

    public CustomerRepository(DatabaseManagerConnector managerConnector) {
        this.managerConnector = managerConnector;
    }

    @Override
    public Optional<CustomerDao> save(CustomerDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getBudget());
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
    public boolean update(CustomerDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.UPDATE_CUSTOMER_BY_ID);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getBudget());
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
    public boolean delete(CustomerDao entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.DELETE_CUSTOMER_BY_ID);
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
    public Optional<CustomerDao> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.prepareStatement(SQLQuery.SELECT_CUSTOMER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return Optional.of(mapCustomerFromResultSet(resultSet));
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
    public List<CustomerDao> findAll() {
        List<CustomerDao> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = managerConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_CUSTOMERS);
            while (resultSet.next()) {
                result.add(mapCustomerFromResultSet(resultSet));
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

    private CustomerDao mapCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setId(resultSet.getInt(ColumnName.ID));
        customerDao.setName(resultSet.getString(ColumnName.NAME));
        customerDao.setBudget(resultSet.getInt(ColumnName.BUDGET));

        return customerDao;
    }
}
