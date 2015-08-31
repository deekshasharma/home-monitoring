package com.homemonitoring.business;

import com.homemonitoring.dao.DBTablesDAO;
import com.homemonitoring.dao.DBConnection;

import java.sql.SQLException;

public class CreateDatabase {

    private DBConnection dbConnection = DBConnection.getInstance();

    public void createDbTables() throws SQLException {
        DBTablesDAO dbTablesDAO = new DBTablesDAO(dbConnection);
        dbTablesDAO.create();
    }
}
